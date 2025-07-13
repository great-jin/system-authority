package xyz.ibudai.authority.manage.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.common.cache.StoreCache;
import xyz.ibudai.authority.core.security.SecurityManager;
import xyz.ibudai.authority.manage.PermitHandler;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StorePermitHandler implements PermitHandler {

    private final StoreCache storeCache;


    @Override
    public boolean hasPermit(Long storeId) {
        Long userId = SecurityManager.getUserId();
        Set<Long> storeIds = storeCache.readByUser(userId);
        return storeIds.contains(storeId);
    }
}
