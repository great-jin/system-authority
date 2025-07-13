package xyz.ibudai.authority.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ibudai.authority.model.entity.RoleStore;

import java.util.List;

/**
 * (TbRoleStore)表服务接口
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
public interface RoleStoreService extends IService<RoleStore> {

    /**
     * 新增角色店铺
     *
     * @param list the list
     */
    void addRoleStore(List<RoleStore> list);

    /**
     * 删除角色店铺
     *
     * @param list the list
     */
    void deleteRoleStore(List<RoleStore> list);

}

