package ooo.klae.sample.motocatalog.commons;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class EventLogFilter {
    
    @Before("execution(* ooo.klae.sample.motocatalog..*Controller.*(..))")
    public void beforeLog(JoinPoint joinPoint) {
        log.info(String.format("%s START", joinPoint.toShortString()));
    }

    @After("execution(* ooo.klae.sample.motocatalog..*Controller.*(..))")
    public void afterLog(JoinPoint joinPoint) {
        log.info(String.format("%s END", joinPoint.toShortString()));
    }
}
