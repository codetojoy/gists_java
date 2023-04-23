package net.codetojoy.logging;

import net.codetojoy.util.Timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoggingAspect {

	@Around("@annotation(net.codetojoy.logging.LogExecutionTime)")
	public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

        Timer timer = new Timer();
        System.out.println("TRACER before call");

		Object result = proceedingJoinPoint.proceed();

		// Log method execution time
		if (timer.doesExceedThreshold()) {
			System.out.println(timer.getElapsed("TRACER execution time: "));
		}

		return result;
	}
}
