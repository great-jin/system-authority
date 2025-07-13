package xyz.ibudai.authority.common.cache;

import org.springframework.stereotype.Component;
import xyz.ibudai.authority.common.enums.CacheOperate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 系统角色菜单缓存
 */
@Component
public class MenuCache extends BaseCache<Long, String> {

    /**
     * Key:     角色ID
     * Value:   角色菜单集合
     */
    private static final Map<Long, Set<String>> MENU_MAP = new ConcurrentHashMap<>();


    /**
     * 根据角色查询菜单权限
     *
     * @param roleId 角色ID
     * @return 菜单权限
     */
    public Set<String> read(Long roleId) {
        return this.read(Set.of(roleId));
    }

    /**
     * 根据角色批量查询菜单权限
     *
     * @param roleIds 角色ID
     * @return 菜单权限
     */
    public Set<String> read(Set<Long> roleIds) {
        return super.read(roleIds, MENU_MAP);
    }

    /**
     * 角色新增权限
     *
     * @param roleId 角色ID
     * @param menus  菜单权限
     */
    public void add(Long roleId, String... menus) {
        add(roleId, Arrays.stream(menus).collect(Collectors.toSet()));
    }

    /**
     * 角色新增权限
     *
     * @param roleId 角色ID
     * @param menus  菜单权限
     */
    public void add(Long roleId, Set<String> menus) {
        super.compute(MENU_MAP, CacheOperate.ADD, roleId, menus);
    }

    /**
     * 角色删除权限
     *
     * @param roleId 角色ID
     * @param menus  菜单权限
     */
    public void delete(Long roleId, String... menus) {
        delete(roleId, Set.of(menus));
    }

    /**
     * 角色删除权限
     *
     * @param roleId 角色ID
     * @param menus  菜单权限
     */
    public void delete(Long roleId, Set<String> menus) {
        super.compute(MENU_MAP, CacheOperate.DELETE, roleId, menus);
    }
}
