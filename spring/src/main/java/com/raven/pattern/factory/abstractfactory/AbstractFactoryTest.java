package com.raven.pattern.factory.abstractfactory;

import com.raven.pattern.factory.ICourse;

/**
 * @PackageName: com.raven.pattern.factory.abstractfactory
 * @ClassName: AbstractFactoryTest
 * @Blame: raven
 * @Date: 2021-06-18 22:22
 * @Description:
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        ICourseFactory courseFactory = JavaCourseFactory.createCourseFactory();
        ICourse course = courseFactory.createCourse();
        ISource source = courseFactory.createSource();
        IVideo video = courseFactory.createVideo();
        course.schooling();
        source.print();
        video.look();
    }
}
