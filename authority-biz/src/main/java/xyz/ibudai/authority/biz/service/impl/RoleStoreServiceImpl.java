package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.ibudai.authority.biz.dao.RoleStoreDao;
import xyz.ibudai.authority.biz.service.RoleStoreService;
import xyz.ibudai.authority.common.cache.StoreCache;
import xyz.ibudai.authority.model.entity.RoleStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (TbUserStore)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Service
@RequiredArgsConstructor
public class RoleStoreServiceImpl extends ServiceImpl<RoleStoreDao, RoleStore> implements RoleStoreService {

    private final StoreCache storeCache;


    @Override
    public void addRoleStore(List<RoleStore> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        // 查询历史数据
        Set<String> recordKeys = new HashSet<>();
        List<RoleStore> histories = this.listHistory(list);
        for (RoleStore item : histories) {
            recordKeys.add(String.valueOf(item.getRoleId()) + item.getStoreId());
        }

        // 过滤保存
        List<RoleStore> addList = new ArrayList<>();
        for (RoleStore item : list) {
            String key = String.valueOf(item.getRoleId()) + item.getStoreId();
            if (recordKeys.contains(key)) {
                // 跳过已存在
                continue;
            }

            addList.add(item);
        }
        this.saveBatch(addList);
        addList.forEach(it -> storeCache.add(it.getRoleId(), it.getStoreId()));
    }

    @Override
    public void deleteRoleStore(List<RoleStore> list) {
        for (RoleStore item : list) {
            this.lambdaUpdate()
                    .eq(RoleStore::getRoleId, item.getRoleId())
                    .eq(RoleStore::getStoreId, item.getStoreId())
                    .remove();
            storeCache.delete(item.getRoleId(), item.getStoreId());
        }
    }


    /**
     * 查询历史记录
     */
    private List<RoleStore> listHistory(List<RoleStore> list) {
        Set<Long> roleIds = new HashSet<>();
        Set<Long> storeIds = new HashSet<>();
        for (RoleStore item : list) {
            roleIds.add(item.getRoleId());
            storeIds.add(item.getStoreId());
        }
        return this.lambdaQuery()
                .in(RoleStore::getRoleId, roleIds)
                .in(RoleStore::getStoreId, storeIds)
                .list();
    }
}

