package xyz.ibudai.authority.common.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import xyz.ibudai.authority.common.enums.CacheOperate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统角色店铺缓存
 */
@Component
@RequiredArgsConstructor
public class StoreCache extends BaseCache<Long, Long> {

    private final UserRoleCache userRoleCache;


    /**
     * Key:     角色ID
     * Value:   角色店铺集合
     */
    private static final Map<Long, Set<Long>> STORE_MAP = new ConcurrentHashMap<>();


    /**
     * 查询用户店铺
     *
     * @param userId 用户ID
     * @return 店铺集合
     */
    public Set<Long> readByUser(Long userId) {
        Set<Long> roleIds = userRoleCache.read(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptySet();
        }

        return readByRole(roleIds);
    }

    /**
     * 查询角色店铺
     *
     * @param roleId 角色ID
     * @return 店铺集合
     */
    public Set<Long> readByRole(Long roleId) {
        return super.read(roleId, STORE_MAP);
    }

    /**
     * 批量查询角色店铺
     *
     * @param roleIds 角色ID
     * @return 店铺集合
     */
    public Set<Long> readByRole(Set<Long> roleIds) {
        return super.read(roleIds, STORE_MAP);
    }

    /**
     * 角色下新增店铺
     *
     * @param roleId   角色ID
     * @param storeIds 店铺集合
     */
    public void add(Long roleId, Set<Long> storeIds) {
        super.compute(STORE_MAP, CacheOperate.ADD, roleId, storeIds);
    }

    /**
     * 角色下删除店铺
     *
     * @param roleId   角色ID
     * @param storeIds 店铺集合
     */
    public void delete(Long roleId, Set<Long> storeIds) {
        super.compute(STORE_MAP, CacheOperate.DELETE, roleId, storeIds);
    }
}
