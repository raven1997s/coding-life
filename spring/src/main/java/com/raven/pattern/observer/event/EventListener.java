package com.raven.pattern.observer.event;

import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @PackageName: com.raven.pattern.observer.event
 * @ClassName: EventListener
 * @Blame: raven
 * @Date: 2021-08-07 20:16
 * @Description:
 */
public class EventListener {

    /**
     * JDK底层的listener通常也是这样设计
     */
    private Map<String, Event> eventMap = Maps.newHashMap();

    public void addListener(String eventType, Object target) {
        try {
            this.addListener(eventType,
                    target,
                    target.getClass().getMethod("on" + toUpperFirstCase(eventType),Event.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private String toUpperFirstCase(String eventType) {
        char[] chars = eventType.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    /**
     * 将事件加入到事件列表中
     * @param eventType
     * @param target
     * @param callback
     */
    private void addListener(String eventType, Object target, Method callback) {
        eventMap.put(eventType, new Event(target, callback));
    }

    /**
     * 事件名称触发
     * @param trigger
     */
    public void trigger(String trigger){
        if (eventMap.containsKey(trigger)){
            this.eventMap.get(trigger).setTrigger(trigger);
            trigger(this.eventMap.get(trigger));
        }
    }

    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            event.getCallback().invoke(event.getTarget(),event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
