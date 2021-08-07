package com.raven.pattern.observer.event;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @PackageName: com.raven.pattern.observer.event
 * @ClassName: Event
 * @Blame: raven
 * @Date: 2021-08-07 20:12
 * @Description:
 */
@Data
public class Event {

    /**
     * 事件源，事件是由谁发起
     */
    private Object source;

    /**
     * 事件触发，要通知谁
     */
    private Object target;

    /**
     * 事件触发，要做什么动作
     */
    private Method callback;

    /**
     * 事件触发，要触发什么事件
     */
    private String trigger;

    private long time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }
}
