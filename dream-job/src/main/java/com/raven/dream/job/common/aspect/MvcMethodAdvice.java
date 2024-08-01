package com.raven.dream.job.common.aspect;

import com.raven.dream.job.common.base.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Lazy
public class MvcMethodAdvice {

    private static final Logger log = LoggerFactory.getLogger(MvcMethodAdvice.class);


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        AspectInfo aspectInfo = AspectInfoFactory.create(joinPoint);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Class controllerMethodReturnClass = AspectUtil.getReturnClass(aspectInfo);
            if (CommonResult.class == controllerMethodReturnClass) {
                result = CommonResult.error(e);
            } else if (String.class == controllerMethodReturnClass) {
                result = CommonResult.error(e).toSimpleString();
            } else {
                result = e;
            }
        }
        return result;
    }

}