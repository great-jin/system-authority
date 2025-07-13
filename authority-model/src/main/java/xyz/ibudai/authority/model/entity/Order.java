package xyz.ibudai.authority.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * (TbOrder)表实体类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:07
 */
@Data
@TableName("tb_order")
public class Order {

    private Long orderId;

    private Long storeId;

    private String productName;

    private LocalDateTime orderTime;

}

