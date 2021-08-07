package com.raven.pattern.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * @PackageName: com.raven.pattern.observer.guava
 * @ClassName: GuavaEventTest
 * @Blame: raven
 * @Date: 2021-08-07 21:19
 * @Description:
 */
public class GuavaEventTest {

    public static void main(String[] args) {

        // 消息总线
        EventBus eventBus = new EventBus();

        // 消息事件
        GuavaEvent guavaEvent = new GuavaEvent();

        // 注册消息事件
        eventBus.register(guavaEvent);

        // 发送消息，实时接收，触发事件
        eventBus.post("战三");

        // jdk的obServer以及spring中的Event都是面向类的， guava是面向方法的。
    }
}
