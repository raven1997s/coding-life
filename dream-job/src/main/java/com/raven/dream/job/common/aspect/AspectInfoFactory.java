package com.raven.dream.job.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Description:
 * date: 2023/6/27 18:48
 *
 * @author longjiaocao
 */
public class AspectInfoFactory {

    private AspectInfoFactory() {
    }

    public static AspectInfo create(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        AspectInfo aspectInfo = new AspectInfo();
        aspectInfo.setMethodParam(pjp.getArgs());
        aspectInfo.setMethodName(methodSignature.getName());
        aspectInfo.setClassType(pjp.getTarget().getClass());
        return aspectInfo;
    }

}