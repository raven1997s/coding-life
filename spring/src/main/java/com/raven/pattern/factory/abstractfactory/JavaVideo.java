package com.raven.pattern.factory.abstractfactory;

/**
 * @PackageName: com.raven.pattern.factory.abstractfactory
 * @ClassName: JavaVideo
 * @Blame: raven
 * @Date: 2021-06-17 20:57
 * @Description:
 */
public class JavaVideo implements IVideo {

    @Override
    public void look() {
        System.out.println("看java视频");
    }
}
