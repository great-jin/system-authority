package xyz.ibudai.authority.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ibudai.authority.model.entity.UserRole;

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
}

