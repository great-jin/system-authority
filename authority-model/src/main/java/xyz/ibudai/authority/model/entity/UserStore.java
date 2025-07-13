package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbUserStore)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Data
@TableName("tb_user_store")
public class UserStore {

    private Long userId;

    private Long storeId;

}

