package com.raven.common.utils;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: qulibin
 * @description: 工具类来从Spring 容器中获取Bean,用于一些不方便采用注入的地方
 * @date: 2:47 PM 2020/3/12
 * @modify：
 */
@Component
public class SpringContextUtil implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        beanFactory = configurableListableBeanFactory;
    }

    public static <T> T getBean(String name) {
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) {
        T result = beanFactory.getBean(clz);
        return result;
    }

    public static <T> List<T> getBeansOfType(Class<T> type) {
        return beanFactory.getBeansOfType(type).entrySet().stream().map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    public static List<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(annotationType);
        return beansWithAnnotation.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
    }
}
