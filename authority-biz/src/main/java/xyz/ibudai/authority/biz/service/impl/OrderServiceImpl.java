package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.ibudai.authority.biz.dao.OrderDao;
import xyz.ibudai.authority.biz.service.OrderService;
import xyz.ibudai.authority.model.entity.Order;
import org.springframework.stereotype.Service;

/**
 * (TbOrder)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

}

