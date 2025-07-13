package xyz.ibudai.authority.manage.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.common.cache.StoreCache;
import xyz.ibudai.authority.core.security.SecurityManager;
import xyz.ibudai.authority.manage.PermitHandler;
import xyz.ibudai.authority.manage.annotation.StorePermit;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StorePermitHandler implements PermitHandler {

    private final StoreCache storeCache;


    @Override
    public String name() {
        return "store";
    }

    @Override
    public boolean lackPermit(Annotation annotation, Object arg) {
        if (Objects.isNull(annotation) || annotation.annotationType() != StorePermit.class) {
            return false;
        }
        if (Objects.isNull(arg)) {
            return true;
        }

        Long userId = SecurityManager.getUserId();
        Set<Long> storeIds = storeCache.readByUser(userId);
        return !storeIds.contains(Long.valueOf(arg.toString()));
    }
}
