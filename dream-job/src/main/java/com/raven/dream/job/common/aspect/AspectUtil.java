package com.raven.dream.job.common.aspect;


import java.lang.reflect.Method;

/**
 * Description:
 * date: 2023/6/28 09:13
 *
 * @author longjiaocao
 */
public class AspectUtil {

    //获取返回值类型
    public static Class getReturnClass(AspectInfo aspectInfo) {
        String methodName = aspectInfo.getMethodName();
        Method[] methods = aspectInfo.getClassType().getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method.getReturnType();
            }
        }
        return null;
    }

}