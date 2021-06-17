package com.raven.pattern.factory;

/**
 * @PackageName: com.raven.pattern.factory.simplefactory
 * @ClassName: JavaCourse
 * @Blame: raven
 * @Date: 2021-06-17 20:57
 * @Description:
 */
public class JavaCourse implements ICourse {
    @Override
    public void schooling() {
        System.out.println("传授java课程！");
    }
}
