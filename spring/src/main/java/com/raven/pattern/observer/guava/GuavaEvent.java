package com.raven.pattern.observer.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @PackageName: com.raven.pattern.observer.guava
 * @ClassName: GuavaEvent
 * @Blame: raven
 * @Date: 2021-08-07 21:18
 * @Description: 定义事件
 */
public class GuavaEvent {

    @Subscribe
    public void register(String name){
        System.out.println(name + "加入了coder论坛！");
    }
}
