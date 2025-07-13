package xyz.ibudai.authority.common.cache;

import org.springframework.stereotype.Component;
import xyz.ibudai.authority.common.enums.CacheOperate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统用户角色缓存
 */
@Component
public class UserRoleCache extends BaseCache<Long, Long> {

    /**
     * Key:     用户ID
     * Value:   用户角色集合
     */
    private static final Map<Long, Set<Long>> UR_MAP = new ConcurrentHashMap<>();


    /**
     * 读取用户角色
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    public Set<Long> read(Long userId) {
        return super.read(userId, UR_MAP);
    }

    /**
     * 用户新增角色
     *
     * @param userId  用户ID
     * @param roleIds 角色集合
     */
    public void add(Long userId, Long... roleIds) {
        this.add(userId, Set.of(roleIds));
    }

    /**
     * 用户新增角色
     *
     * @param userId  用户ID
     * @param roleIds 角色集合
     */
    public void add(Long userId, Set<Long> roleIds) {
        super.compute(UR_MAP, CacheOperate.ADD, userId, roleIds);
    }

    /**
     * 用户删除角色
     *
     * @param userId  用户ID
     * @param roleIds 角色集合
     */
    public void delete(Long userId, Long... roleIds) {
        this.delete(userId, Set.of(roleIds));
    }

    /**
     * 用户删除角色
     *
     * @param userId  用户ID
     * @param roleIds 角色集合
     */
    public void delete(Long userId, Set<Long> roleIds) {
        super.compute(UR_MAP, CacheOperate.DELETE, userId, roleIds);
    }
}
