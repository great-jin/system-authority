package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbRoleMenu)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Data
@TableName("tb_role_menu")
public class RoleMenu {

    private Long roleId;

    private String menuKey;

}

