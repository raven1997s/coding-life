package com.raven.pattern.factory;

/**
 * @PackageName: com.raven.pattern.factory.simplefactory
 * @ClassName: GoCourse
 * @Blame: raven
 * @Date: 2021-06-17 20:57
 * @Description:
 */
public class GoCourse implements ICourse {
    @Override
    public void schooling() {
        System.out.println("传授go课程！");
    }
}
