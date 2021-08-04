package com.raven.pattern.template.course;

/**
 * @PackageName: com.raven.pattern.template.course
 * @ClassName: GoCourse
 * @Blame: raven
 * @Date: 2021-08-03 17:08
 * @Description:
 */
public class GoCourse extends AbstractNetworkCourse {

    @Override
    void submitJob() {
        System.out.println("Go 语言不需要作业");
    }
}
