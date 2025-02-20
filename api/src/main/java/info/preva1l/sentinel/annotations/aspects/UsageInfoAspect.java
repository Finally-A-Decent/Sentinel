package info.preva1l.sentinel.annotations.aspects;

import info.preva1l.sentinel.annotations.UsageInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.logging.Logger;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
@Aspect
public class UsageInfoAspect {
    private static final Logger logger = Logger.getLogger("LogSentinel API");

    @Pointcut("execution(* info.preva1l.sentinel..*(..))")
    public void anyMethodInTargetPackage() {}

    @Before("anyMethodInTargetPackage()")
    public void interceptMethod(JoinPoint joinPoint) {
        try {
            usageInfo(joinPoint);
            enumUsageInfo(joinPoint);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private void usageInfo(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        var annotations = signature.getMethod().getAnnotationsByType(UsageInfo.class);
        if (annotations.length == 0) return;
        log(annotations, signature.getName(), signature.getDeclaringType().getSimpleName(), joinPoint);
    }

    private void enumUsageInfo(JoinPoint joinPoint) throws NoSuchFieldException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var args = joinPoint.getArgs();
        var parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            var parameter = parameters[i];
            if (!parameter.getType().isEnum()) continue;

            var enumValue = (Enum<?>) args[i];
            var annotations = enumValue.getClass().getField(enumValue.name()).getAnnotationsByType(UsageInfo.class);
            if (annotations.length == 0) return;
            log(annotations, signature.getName(), signature.getDeclaringType().getSimpleName(), joinPoint);
        }
    }

    private void log(UsageInfo[] annotations, String className, String methodName, JoinPoint joinPoint) {
        for (UsageInfo annotation : annotations) {
            switch (annotation.logLevel()) {
                case INFO -> logger.info(annotation.value() + " [" + className + "#" + methodName + "(" + joinPoint.getSourceLocation().toString() + ")]");
                case WARNING -> logger.warning(annotation.value() + " [" + className + "#" + methodName + "(" + joinPoint.getSourceLocation().toString() + ")]");
                case FATAL -> logger.severe(annotation.value() + " [" + className + "#" + methodName + "(" + joinPoint.getSourceLocation().toString() + ")]");
            }
        }
    }
}
