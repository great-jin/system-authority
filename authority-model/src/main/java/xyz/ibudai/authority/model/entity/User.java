package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbUser)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:07
 */
@Data
@TableName("tb_user")
public class User {

    private Long userId;

    private String username;

    private String password;

}

