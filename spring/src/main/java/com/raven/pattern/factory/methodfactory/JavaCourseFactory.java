package com.raven.pattern.factory.methodfactory;

import com.raven.pattern.factory.ICourse;
import com.raven.pattern.factory.JavaCourse;

/**
 * @PackageName: com.raven.pattern.factory.simplefactory
 * @ClassName: CourseFactory
 * @Blame: raven
 * @Date: 2021-06-17 21:00
 * @Description: 创建课程对象简单工厂
 */
public class JavaCourseFactory  implements IFactory{

    private static JavaCourseFactory javaCourseFactory;

    private JavaCourseFactory() {
    }

    /**
     * DCL双检查锁机制（DCL：double checked locking）
     *
     * @return
     */
    public static JavaCourseFactory createCourseFactory() {
        if (javaCourseFactory == null) {
            synchronized (JavaCourseFactory.class) {
                if (javaCourseFactory == null) {
                    javaCourseFactory = new JavaCourseFactory();
                }
            }
        }
        return javaCourseFactory;
    }


    /**
     * 单一职责，创建java课程对象
     * @return
     */
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }
}
