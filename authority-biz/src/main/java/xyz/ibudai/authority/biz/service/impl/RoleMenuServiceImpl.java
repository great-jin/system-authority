package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xyz.ibudai.authority.biz.dao.RoleMenuDao;
import xyz.ibudai.authority.biz.service.RoleMenuService;
import xyz.ibudai.authority.common.cache.MenuCache;
import xyz.ibudai.authority.model.entity.RoleMenu;

import java.util.*;

/**
 * (TbUserStore)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

    private final MenuCache menuCache;


    @Override
    public void addRoleMenu(List<RoleMenu> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        // 查询历史数据
        Set<String> recordKeys = new HashSet<>();
        List<RoleMenu> histories = this.listHistory(list);
        for (RoleMenu item : histories) {
            recordKeys.add(item.getRoleId() + item.getMenuKey());
        }

        // 过滤保存
        List<RoleMenu> addList = new ArrayList<>();
        for (RoleMenu item : list) {
            String key = item.getRoleId() + item.getMenuKey();
            if (recordKeys.contains(key)) {
                // 跳过已存在
                continue;
            }

            addList.add(item);
        }
        this.saveBatch(addList);
        addList.forEach(it -> menuCache.add(it.getRoleId(), it.getMenuKey()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenu(List<RoleMenu> list) {
        for (RoleMenu item : list) {
            this.lambdaUpdate()
                    .eq(RoleMenu::getRoleId, item.getRoleId())
                    .eq(RoleMenu::getMenuKey, item.getMenuKey())
                    .remove();
            menuCache.delete(item.getRoleId(), item.getMenuKey());
        }
    }


    /**
     * 查询历史记录
     */
    private List<RoleMenu> listHistory(List<RoleMenu> list) {
        Set<Long> roleIds = new HashSet<>();
        Set<String> menuKeys = new HashSet<>();
        for (RoleMenu item : list) {
            roleIds.add(item.getRoleId());
            menuKeys.add(item.getMenuKey());
        }
        return this.lambdaQuery()
                .in(RoleMenu::getRoleId, roleIds)
                .in(RoleMenu::getMenuKey, menuKeys)
                .list();
    }
}

