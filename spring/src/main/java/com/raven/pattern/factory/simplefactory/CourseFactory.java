package com.raven.pattern.factory.simplefactory;

import com.raven.pattern.factory.GoCourse;
import com.raven.pattern.factory.ICourse;
import com.raven.pattern.factory.JavaCourse;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @PackageName: com.raven.pattern.factory.simplefactory
 * @ClassName: CourseFactory
 * @Blame: raven
 * @Date: 2021-06-17 21:00
 * @Description: 创建课程对象简单工厂
 */
public class CourseFactory {

    private static volatile CourseFactory courseFactory = null;

    private CourseFactory() {
    }

    /**
     * DCL双检查锁机制（DCL：double checked locking）
     *
     * @return
     */
    public static CourseFactory getInstance() {
        if (courseFactory == null) {
            synchronized (CourseFactory.class) {
                if (courseFactory == null) {
                    courseFactory = new CourseFactory();
                }
            }
        }
        return courseFactory;
    }


    /**
     * 通过传递课程实体类的class对象创建 课程实体类
     *
     * @param clazz
     * @return
     */
    public ICourse createICourse(Class clazz) {
        if (Objects.nonNull(clazz)) {
            try {
                return (ICourse) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过传入课程名字的方式创建工厂对象
     *
     * @param courseName
     * @return
     */
    public ICourse createICourse(String courseName) {
        if (StringUtils.equals("java", courseName)) {
            return new JavaCourse();
        } else if (StringUtils.equals("go", courseName)) {
            return new GoCourse();
        }
        return null;
    }
}
