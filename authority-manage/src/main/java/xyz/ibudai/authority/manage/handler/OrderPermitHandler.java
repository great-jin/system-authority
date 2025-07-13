package xyz.ibudai.authority.manage.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.biz.service.OrderService;
import xyz.ibudai.authority.common.cache.StoreCache;
import xyz.ibudai.authority.core.security.SecurityManager;
import xyz.ibudai.authority.manage.PermitHandler;
import xyz.ibudai.authority.manage.annotation.OrderPermit;
import xyz.ibudai.authority.model.entity.Order;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderPermitHandler implements PermitHandler {

    private final StoreCache storeCache;
    private final OrderService orderService;


    @Override
    public String name() {
        return "order";
    }

    @Override
    public boolean lackPermit(Annotation annotation, Object arg) {
        if (Objects.isNull(annotation) || annotation.annotationType() != OrderPermit.class) {
            return false;
        }
        if (Objects.isNull(arg)) {
            return true;
        }

        Order order = orderService.lambdaQuery()
                .eq(Order::getOrderId, Long.valueOf(arg.toString()))
                .one();
        if (Objects.isNull(order)) {
            return true;
        }
        Long storeId = order.getStoreId();
        if (Objects.isNull(storeId)) {
            return true;
        }

        Long userId = SecurityManager.getUserId();
        Set<Long> storeIds = storeCache.readByUser(userId);
        return !storeIds.contains(storeId);
    }
}
