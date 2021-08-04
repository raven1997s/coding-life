package com.raven.pattern.template.course;

/**
 * @PackageName: com.raven.pattern.template.course
 * @ClassName: CourseTest
 * @Blame: raven
 * @Date: 2021-08-03 17:12
 * @Description:
 */
public class CourseTest {

    public static void main(String[] args) {

        System.out.println("GO GO GO !");
        GoCourse goCourse = new GoCourse();
        goCourse.createCourse();

        System.out.println("==========================");
        System.out.println("JAVA JAVA JAVA !");
        JavaCourse javaCourse = new JavaCourse(true);
        javaCourse.createCourse();

        System.out.println("==========================");
        JavaCourse javaCourse2 = new JavaCourse(false);
        javaCourse2.createCourse();

    }
}
