package com.raven.proxy.dynamicproxy.cglib;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @PackageName: com.raven.proxy.dynamicproxy.cglib
 * @ClassName: CglibFactory
 * @Blame: raven
 * @Date: 2021-05-27 16:10
 * @Description:
 */
public class CglibFactory implements MethodInterceptor {

    private Object target;

    public CglibFactory() {
    }

    public CglibFactory(Object target) {
        this.target = target;
    }

    /**
     * Enhancer是cglib中使用频率很高的一个类，它是一个字节码增强器，可以用来为无接口的类创建代理。
     * 它的功能与java自带的Proxy类挺相似的。它会根据某个给定的类创建子类，并且所有非final的方法都带有回调钩子。
     * 创建增强对象 并给动态代理对象指定当前类为父类
     * @return
     */
    public Object createProxy(){
        Enhancer enhancer = new Enhancer();
        // 设置enhancer的父类
        enhancer.setSuperclass(target.getClass());
        // 设置enhancer的回调函数 intercept方法
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (StringUtils.equals(method.getName(),"eat")){
            System.out.println("前置加强");
            Object invoke = method.invoke(target, args);
            System.out.println("后置加强");
            return invoke;
        }
        return null;
    }
}
