package net.codetojoy.logging;

import net.codetojoy.util.Timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("@annotation(net.codetojoy.logging.LogExecutionTime)")
	public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

        Timer timer = new Timer();

		Object result = proceedingJoinPoint.proceed();

		// Log method execution time
		if (timer.doesExceedThreshold()) {
			logger.info(timer.getElapsed("TRACER execution time: "));
		}

		return result;
	}
}
