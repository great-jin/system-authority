package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbRole)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:06
 */
@Data
@TableName("tb_role")
public class Role {

    private Long roleId;

    private String roleName;

}

