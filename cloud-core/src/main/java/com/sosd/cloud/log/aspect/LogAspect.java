package com.sosd.cloud.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect //切面类
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.sosd.cloud.log.annotation.Log)")
    public void logAnnotatedMethods() {
    }



    @Around("logAnnotatedMethods()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        LocalDateTime time=LocalDateTime.now();
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // continue on the intercepted method
        long endTime = System.currentTimeMillis();

        Boolean isSuccess=true;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String[] args = Arrays.stream(joinPoint.getArgs()).map(Object::toString).toArray(String[]::new);

        try {
            result = joinPoint.proceed(); // continue on the intercepted method
        } catch (Throwable e) {
            isSuccess = false;
            // 记录ERROR日志，并稍后重新抛出异常
            logger.error("接口调用发生异常，接口名称：{}", requestURI, e);
            throw e;
        } finally {


            // 总是记录INFO级别的日志
            logger.info("\n===================\n" +
                            "接口名称：{}\n" +
                            "调用时间：{}\n" +
                            "传参：{}\n" +
                            "调用类型：{}\n" +
                            "接口响应时间：{} ms\n" +
                            "是否调用成功：{}\n" +
                            "===================",
                    requestURI,
                    time,
                    Arrays.toString(args),
                    requestMethod,
                    (endTime - startTime),
                    isSuccess


            );

            // 模拟WARN日志条件：如果方法执行时间过长，则记录WARN日志
            long executionTime = endTime - startTime;
            if (executionTime > 5000) { // 假设超过5秒为长时间执行
                logger.warn("接口调用执行时间过长，接口名称：{}，执行时间：{} ms", requestURI, executionTime);
            }

            // 假设ERROR条件：如果结果是特定的错误代码，则记录ERROR日志（这个条件应该是基于你的业务逻辑）
            if (result instanceof Integer && (Integer) result == -1) {
                logger.error("接口调用返回错误代码，接口名称：{}，错误代码：{}", requestURI, result);
            }
        }

        return result;
    }


    @AfterThrowing(pointcut = "logAnnotatedMethods()",throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        log.error("Exception in method: {}", joinPoint.getSignature().getName(), exception);
    }


}
