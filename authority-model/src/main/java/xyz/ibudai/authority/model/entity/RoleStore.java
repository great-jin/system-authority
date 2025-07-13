package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbRoleStore)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Data
@TableName("tb_role_store")
public class RoleStore {

    private Long roleId;

    private Long storeId;

}

