package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (TbStore)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Data
@TableName("tb_store")
public class Store {

    private Long storeId;

    private String storeName;
}

