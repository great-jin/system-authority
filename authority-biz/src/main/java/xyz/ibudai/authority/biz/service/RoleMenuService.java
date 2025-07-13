package xyz.ibudai.authority.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ibudai.authority.model.entity.RoleMenu;

import java.util.List;


/**
 * (TbRoleMenu)表服务接口
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 新增角色菜单
     *
     * @param list the list
     */
    void addRoleMenu(List<RoleMenu> list);

    /**
     * 删除角色菜单
     *
     * @param list the list
     */
    void deleteRoleMenu(List<RoleMenu> list);

}

