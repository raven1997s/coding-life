package com.raven.pattern.observer.event;

/**
 * @PackageName: com.raven.pattern.observer.event
 * @ClassName: Mouse
 * @Blame: raven
 * @Date: 2021-08-07 20:31
 * @Description:
 */
public class Mouse extends EventListener {

    public void click(){
        System.out.println("调用单击方法");
        trigger(MouseEventType.ON_CLICK);
    }

    public void doubleClick(){
        System.out.println("调用双击方法");
        trigger(MouseEventType.ON_DOUBLE_CLICK);
    }
}
