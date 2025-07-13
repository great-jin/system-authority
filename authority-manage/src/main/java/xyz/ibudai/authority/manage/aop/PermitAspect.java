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
import xyz.ibudai.authority.model.base.ResultData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class PermitAspect {

    private final Map<String, PermitHandler> permitHandlerMap;


    @Pointcut("execution (public * xyz.ibudai.authority.rest.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        if (Objects.isNull(method)) {
            return joinPoint.proceed();
        }

        Object[] args = joinPoint.getArgs();
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object arg = args[i];
            for (Annotation annotation : annotations[i]) {
                for (Map.Entry<String, PermitHandler> entry : permitHandlerMap.entrySet()) {
                    PermitHandler handler = entry.getValue();
                    boolean lackPermit = handler.lackPermit(annotation, arg);
                    if (lackPermit) {
                        return ResultData.denies(String.format("Lack %s permission of %s", handler.name(), arg));
                    }
                }
            }
        }
        // 校验合法，放行
        return joinPoint.proceed();
    }


    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature methodSignature)) {
            return null;
        }

        // 如果是代理对象，取真实方法
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget()
                        .getClass()
                        .getDeclaredMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                return null;
            }
        }
        return method;
    }
}
