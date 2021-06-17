package com.raven.pattern.factory.methodfactory;

import com.raven.pattern.factory.ICourse;

/**
 * @PackageName: com.raven.pattern.factory.methodfactory
 * @ClassName: MethodFactoryTest
 * @Blame: raven
 * @Date: 2021-06-17 21:43
 * @Description:方法工厂模式Demo
 */
public class MethodFactoryTest {

    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory = JavaCourseFactory.createCourseFactory();
        ICourse javaCourse = javaCourseFactory.createCourse();
        javaCourse.schooling();
        // 顺便验证单例模式创建工厂对象
        JavaCourseFactory javaCourseFactory2 = JavaCourseFactory.createCourseFactory();
        System.out.println(javaCourseFactory);
        System.out.println(javaCourseFactory2);
    }
}
