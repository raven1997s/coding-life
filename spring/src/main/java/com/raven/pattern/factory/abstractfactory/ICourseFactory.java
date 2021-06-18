package com.raven.pattern.factory.abstractfactory;

import com.raven.pattern.factory.ICourse;

/**
 * @PackageName: com.raven.pattern.factory.abstractfactory
 * @ClassName: ICourseFactory
 * @Blame: raven
 * @Date: 2021-06-18 22:06
 * @Description:抽象工厂顶级父类 抽象工厂不满足开闭原则，因为他拓展性虽然强(便于新增产品等级结构)，但是对修改没有关闭，当顶级工厂产品族新增时，需要修改大量代码
 */
public interface ICourseFactory {
    /**
     * 创建课程
     *
     * @return
     */
    ICourse createCourse();

    /**
     * 创建源码
     *
     * @return
     */
    ISource createSource();

    /**
     * 创建视频
     *
     * @return
     */
    IVideo createVideo();
}
