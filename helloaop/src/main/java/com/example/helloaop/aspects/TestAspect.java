package com.example.helloaop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class TestAspect {

    @Around("execution(* com.example.helloaop.service..*Service.*(..))")
    public Object testAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin  = System.currentTimeMillis();

        Object retVal = proceedingJoinPoint.proceed();

        log.info("service 패키지 내부에 있는 모든 Service 클래스의 모든 메서드가 실행 될때 마다 이 메서드를 적용하겠다.");

        long spendingTime = System.currentTimeMillis() - begin;

        log.info("spendingTime={}", spendingTime);

        return retVal;
    }

    @Around("execution(* com.example.helloaop.service..*Service.only())")
    public Object onlyMethodAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();

        Object retVal = proceedingJoinPoint.proceed();

        log.info("service 패키지 내부에 있는 모든 Service 클래스의 매개변수 없는 only() 메서드만 실행 하겠다. ");

        long spendingTime = System.currentTimeMillis() - begin;

        log.info("spendingTime={}", spendingTime);

        return retVal;
    }

    @Around("execution(* com.example.helloaop.service..Test2Service.only())")
    public Object onlyMethodInTest2SvcAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();

        Object retVal = proceedingJoinPoint.proceed();

        log.info("service 패키지 내부에 있는 오직 Test2Service 클래스의 매개변수 없는 only() 메서드만 실행 하겠다. ");

        long spendingTime = System.currentTimeMillis() - begin;

        log.info("spendingTime={}", spendingTime);

        return retVal;
    }

    @Around("execution(* com.example.helloaop.service..*Service.only(*))")
    public Object onlyMethodWithOneParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        Object retVal = proceedingJoinPoint.proceed();

        log.info("service 패키지 내부에 있는 모든 Service 클래스의 하나의 인자를 가지고 있는 only(num) 메서드만 실행하겠다.");

        long spendingTime = System.currentTimeMillis() - begin;

        log.info("spendingTime={}", spendingTime);

        return retVal;
    }

    @Around("execution(* com.example.helloaop.service..*Service.only(*,*))")
    public Object onlyMethodWithTwoParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String kind = proceedingJoinPoint.getKind();
        String method = proceedingJoinPoint.getSignature().getName();
        Object args = proceedingJoinPoint.getArgs();


        long begin = System.currentTimeMillis();

        log.info("service 패키지 내부에 있는 모든 Service 클래스의 두개의 인자를 가지고 있는 only(num, text) 메서드만 실행하겠다.");

        long spendingTime = System.currentTimeMillis() - begin;

        log.info("spendingTime={}", spendingTime);

        Object[] finalArgs = Arrays.stream(proceedingJoinPoint.getArgs()).map(data ->{
            if(data instanceof String) {
                return data + " 수정";
            }else{
                return data;
            }
        }).toArray();

        log.info("finalArgs={}", finalArgs);



        Object retVal = proceedingJoinPoint.proceed(finalArgs);


        return retVal;
    }



}
