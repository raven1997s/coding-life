package com.raven.common.utils;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @Author yiliang
 * @Date 2020/8/22
 */
@Slf4j
public class RequestUtils {

    private RequestUtils(){

    }
    /**
     * 将 HttpServletRequest 中的参数反射至实体类
     * @param <T>
     * @param request
     * @return
     */
    public static <T> void reflectParameter(T obj, HttpServletRequest request) {
        try {
            //获取对象的Class
            Class<?> clazz = obj.getClass();
            //获取Class中所有已声明的属性集合
            Field[] fields = clazz.getDeclaredFields();
            //遍历属性结合
            for (Field f : fields) {
                //过滤被final、static修饰的成员变量
                if ((f.getModifiers() & Modifier.FINAL) > 0
                        || (f.getModifiers() & Modifier.STATIC) > 0) {
                    continue;
                }
                //取消所有私有变量的限制
                f.setAccessible(true);
                //获取属性的类型，long/int/string....
                Class<?> fieldType = f.getType();
                //获取属性的名字
                String fieldName = f.getName();
                //根据属性的名字从request中获取value
                String paramVal = request.getParameter(fieldName);
                //判断类型
                if (String.class == fieldType) {
                    f.set(obj, paramVal);
                } else if (long.class == fieldType || Long.class == fieldType) {
                    f.set(obj, Convert.toLong(paramVal));
                } else if (int.class == fieldType || Integer.class == fieldType) {
                    f.set(obj, Convert.toInt(paramVal));
                } else if (Date.class == fieldType) {
                    f.set(obj, Convert.toDate(paramVal));
                }
            }
        } catch (IllegalArgumentException e) {
            log.error("HttpServletRequest 转实体类失败[参数错误]", e);
        } catch (IllegalAccessException e) {
            log.error("HttpServletRequest 转实体类失败[访问符错误]", e);
        }
    }
}
