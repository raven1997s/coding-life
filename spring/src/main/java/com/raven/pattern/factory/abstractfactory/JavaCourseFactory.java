package com.raven.pattern.factory.abstractfactory;

import com.raven.pattern.factory.ICourse;
import com.raven.pattern.factory.JavaCourse;

/**
 * @PackageName: com.raven.pattern.factory.abstractfactory
 * @ClassName: JavaCourseFactory
 * @Blame: raven
 * @Date: 2021-06-17 21:00
 * @Description: 创建课程对象简单工厂
 */
public class JavaCourseFactory implements ICourseFactory {

    private static volatile JavaCourseFactory javaCourseFactory = null;

    private JavaCourseFactory() {
    }

    /**
     * DCL双检查锁机制（DCL：double checked locking）
     *
     * @return
     */
    public static JavaCourseFactory getInstance() {
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
     *
     * @return
     */
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }

    @Override
    public ISource createSource() {
        return new JavaSource();
    }

    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }
}
