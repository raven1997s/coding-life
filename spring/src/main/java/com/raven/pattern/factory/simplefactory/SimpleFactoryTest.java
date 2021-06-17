package com.raven.pattern.factory.simplefactory;

import com.raven.pattern.factory.GoCourse;
import com.raven.pattern.factory.ICourse;

/**
 * @PackageName: com.raven.pattern.factory.simplefactory
 * @ClassName: SimpleFactoryTest
 * @Blame: raven
 * @Date: 2021-06-17 20:58
 * @Description: 简单工厂对象Demo
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
//        // 普通的new 对象的方式
//        ICourse course = new JavaCourse();
//        course.schooling();

        // 通过传入课程名字的方式创建课程对象
//        CourseFactory courseFactory = CourseFactory.createCourseFactory();
//        ICourse goCourse = courseFactory.createICourse("go");
//        goCourse.schooling();
//        ICourse javaCourse = courseFactory.createICourse("java");
//        javaCourse.schooling();

        // 通过传入课程对象的实现类对象的class对象创建课程对象
        CourseFactory courseFactory = CourseFactory.createCourseFactory();
        ICourse goCourse = courseFactory.createICourse(GoCourse.class);
        goCourse.schooling();

    }

}
