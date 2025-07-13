package xyz.ibudai.authority.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ibudai.authority.biz.service.OrderService;
import xyz.ibudai.authority.manage.annotation.OrderPermit;
import xyz.ibudai.authority.model.base.ResultData;
import xyz.ibudai.authority.model.entity.Order;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;


    @GetMapping("{orderId}")
    @PreAuthorize("@pm.hasPermit('order.query')")
    public ResultData<Order> query(@OrderPermit @PathVariable String orderId) {
        Order order = orderService.lambdaQuery()
                .eq(Order::getOrderId, orderId)
                .one();
        return ResultData.success(order);
    }
}
