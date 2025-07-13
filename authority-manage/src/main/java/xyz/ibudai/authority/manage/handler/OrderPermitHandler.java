package xyz.ibudai.authority.manage.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.biz.service.OrderService;
import xyz.ibudai.authority.common.cache.StoreCache;
import xyz.ibudai.authority.core.security.SecurityManager;
import xyz.ibudai.authority.manage.PermitHandler;
import xyz.ibudai.authority.model.entity.Order;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderPermitHandler implements PermitHandler {

    private final StoreCache storeCache;
    private final OrderService orderService;


    @Override
    public boolean hasPermit(Long orderId) {
        Order order = orderService.lambdaQuery()
                .eq(Order::getOrderId, orderId)
                .one();
        if (Objects.isNull(order)) {
            return false;
        }
        Long storeId = order.getStoreId();
        if (Objects.isNull(storeId)) {
            return false;
        }

        Long userId = SecurityManager.getUserId();
        Set<Long> storeIds = storeCache.readByUser(userId);
        return storeIds.contains(storeId);
    }
}
