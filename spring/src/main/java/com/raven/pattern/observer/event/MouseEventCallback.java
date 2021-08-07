package com.raven.pattern.observer.event;

/**
 * @PackageName: com.raven.pattern.observer.event
 * @ClassName: MouseEventCallback
 * @Blame: raven
 * @Date: 2021-08-07 20:35
 * @Description:
 */
public class MouseEventCallback {

    public void onClick(Event event){
        System.out.println("=======触发鼠标单击事件======" + "\n" + event);
    }
    public void onDoubleClick(Event event){
        System.out.println("=======触发鼠标双击事件======" + "\n" + event);
    }
}
