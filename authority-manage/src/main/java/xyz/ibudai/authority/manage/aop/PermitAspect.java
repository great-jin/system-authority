package xyz.ibudai.authority.manage.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.manage.PermitHandler;
import xyz.ibudai.authority.manage.annotation.StorePermit;
import xyz.ibudai.authority.model.base.ResultData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class PermitAspect {

    private final PermitHandler storePermitHandler;


    @Pointcut("execution (public * xyz.ibudai.authority.rest.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        if (Objects.isNull(method)) {
            return joinPoint.proceed();
        }

        Annotation[][] annotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType() != StorePermit.class) {
                    continue;
                }
                Object arg = args[i];
                if (Objects.isNull(arg)) {
                    continue;
                }

                Long storeId = Long.valueOf(arg.toString());
                boolean hasPermit = storePermitHandler.hasPermit(storeId);
                if (!hasPermit) {
                    return ResultData.failed("Lack store permit");
                }
            }
        }

        return joinPoint.proceed();
    }


    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature methodSignature)) {
            return null;
        }
        Method method = methodSignature.getMethod();

        // 如果是代理对象，取真实方法
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass()
                        .getDeclaredMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                return null;
            }
        }
        return method;
    }
}
