package xyz.ibudai.authority.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ibudai.authority.model.entity.UserRole;

import java.util.List;
import java.util.Set;

/**
 * (SysUserRole)表服务接口
 *
 * @author ibudai
 * @since 2025-07-12 22:22:07
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 查询用户关联角色的菜单权限
     *
     * @param userId 用户
     * @return 菜单
     */
    Set<String> getUserMenus(Long userId);


    /**
     * 新增用户角色
     *
     * @param list the list
     */
    void addUserRole(List<UserRole> list);

    /**
     * 删除用户角色
     *
     * @param list the list
     */
    void deleteUserRole(List<UserRole> list);

}

