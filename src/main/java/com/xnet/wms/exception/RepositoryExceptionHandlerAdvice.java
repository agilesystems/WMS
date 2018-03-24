/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.exception;

import javassist.bytecode.SignatureAttribute.MethodSignature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author ramy
 */
@Aspect
@Component
public class RepositoryExceptionHandlerAdvice {

    @Around("execution( * com.xnet.wms.repository.*.*(..))")
    public Object invokeService(ProceedingJoinPoint pjp) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
         Object returnValue = null;
        try {
            returnValue = pjp.proceed();
        } catch (Exception e) {
            // handle the exception 
            System.out.println("exxxxxxxxxxx>>"+e.getMessage());
        } finally {
        }
        return returnValue;
    }
}
