package xyz.ibudai.authority.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import xyz.ibudai.authority.biz.service.RoleMenuService;
import xyz.ibudai.authority.biz.service.RoleStoreService;
import xyz.ibudai.authority.common.cache.MenuCache;
import xyz.ibudai.authority.common.cache.StoreCache;
import xyz.ibudai.authority.model.entity.RoleMenu;
import xyz.ibudai.authority.model.entity.RoleStore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermitRunner implements ApplicationRunner {

    private final MenuCache menuCache;
    private final StoreCache storeCache;

    private final RoleMenuService roleMenuService;
    private final RoleStoreService roleStoreService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.cacheMenu();

        this.cacheStore();
    }


    /**
     * 缓存角色菜单权限
     */
    private void cacheMenu() {
        List<RoleMenu> list = roleMenuService.lambdaQuery().list();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        Map<Long, Set<String>> menuGroups = list.stream()
                .collect(Collectors.groupingBy(RoleMenu::getRoleId,
                        Collectors.mapping(RoleMenu::getMenuKey, Collectors.toSet()))
                );
        for (Map.Entry<Long, Set<String>> entry : menuGroups.entrySet()) {
            menuCache.add(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 缓存角色店铺权限
     */
    private void cacheStore() {
        List<RoleStore> list = roleStoreService.lambdaQuery().list();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        Map<Long, Set<Long>> storeGroups = list.stream()
                .collect(Collectors.groupingBy(RoleStore::getRoleId,
                        Collectors.mapping(RoleStore::getStoreId, Collectors.toSet()))
                );
        for (Map.Entry<Long, Set<Long>> entry : storeGroups.entrySet()) {
            storeCache.add(entry.getKey(), entry.getValue());
        }
    }
}
