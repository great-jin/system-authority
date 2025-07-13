package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbUserRole)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:07
 */
@Data
@TableName("tb_user_role")
public class UserRole {

    private Long userId;

    private Long roleId;

}

