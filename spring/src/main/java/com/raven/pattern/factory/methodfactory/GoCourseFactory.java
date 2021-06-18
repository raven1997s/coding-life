package com.raven.pattern.factory.methodfactory;

import com.raven.pattern.factory.GoCourse;
import com.raven.pattern.factory.ICourse;

/**
 * @PackageName: com.raven.pattern.factory.methodfactory
 * @ClassName: GoCourseFactory
 * @Blame: raven
 * @Date: 2021-06-17 21:00
 * @Description: 创建GO课程对象方法工厂
 */
public class GoCourseFactory implements IFactory{

    private static GoCourseFactory goCourseFactory;

    private GoCourseFactory() {
    }

    /**
     * DCL双检查锁机制（DCL：double checked locking）
     *
     * @return
     */
    public static GoCourseFactory createCourseFactory() {
        if (goCourseFactory == null) {
            synchronized (GoCourseFactory.class) {
                if (goCourseFactory == null) {
                    goCourseFactory = new GoCourseFactory();
                }
            }
        }
        return goCourseFactory;
    }


    /**
     * 单一职责，创建go课程对象
     * @return
     */
    @Override
    public ICourse createCourse() {
        return new GoCourse();
    }
}
