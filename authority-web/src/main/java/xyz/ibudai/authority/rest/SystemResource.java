package xyz.ibudai.authority.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ibudai.authority.biz.service.RoleMenuService;
import xyz.ibudai.authority.biz.service.RoleStoreService;
import xyz.ibudai.authority.biz.service.UserRoleService;
import xyz.ibudai.authority.model.base.ResultData;
import xyz.ibudai.authority.model.entity.RoleMenu;
import xyz.ibudai.authority.model.entity.RoleStore;
import xyz.ibudai.authority.model.entity.UserRole;

import java.util.List;

/**
 * The type System resource.
 */
@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class SystemResource {

    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;
    private final RoleStoreService roleStoreService;


    /**
     * 新增用户角色
     *
     * @param list the list
     * @return the result data
     */
    @PostMapping("addUserRole")
    public ResultData<Void> addUserRole(@RequestBody List<UserRole> list) {
        userRoleService.addUserRole(list);
        return ResultData.success();
    }

    /**
     * 删除用户角色
     *
     * @param list the list
     * @return the result data
     */
    @PostMapping("deleteUserRole")
    public ResultData<Void> deleteUserRole(@RequestBody List<UserRole> list) {
        userRoleService.deleteUserRole(list);
        return ResultData.success();
    }

    /**
     * 新增角色菜单
     *
     * @param list the list
     * @return the result data
     */
    @PostMapping("addRoleMenu")
    public ResultData<Void> addRoleMenu(@RequestBody List<RoleMenu> list) {
        roleMenuService.addRoleMenu(list);
        return ResultData.success();
    }

    /**
     * 删除角色菜单
     *
     * @param list the list
     * @return the result data
     */
    @PostMapping("deleteRoleMenu")
    public ResultData<Void> deleteRoleMenu(@RequestBody List<RoleMenu> list) {
        roleMenuService.deleteRoleMenu(list);
        return ResultData.success();
    }

    /**
     * 新增角色店铺
     *
     * @param list the list
     * @return the result data
     */
    @PostMapping("addRoleStore")
    public ResultData<Void> addRoleStore(@RequestBody List<RoleStore> list) {
        roleStoreService.addRoleStore(list);
        return ResultData.success();
    }

    /**
     * 删除角色店铺
     *
     * @param list the list
     * @return the result data
     */
    @PostMapping("deleteRoleStore")
    public ResultData<Void> deleteRoleStore(@RequestBody List<RoleStore> list) {
        roleStoreService.deleteRoleStore(list);
        return ResultData.success();
    }
}
