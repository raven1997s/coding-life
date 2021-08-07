package com.raven.pattern.observer.event;

/**
 * @PackageName: com.raven.pattern.observer.event
 * @ClassName: MouseEventTest
 * @Blame: raven
 * @Date: 2021-08-07 20:39
 * @Description:
 */
public class MouseEventTest {

    public static void main(String[] args) throws NoSuchMethodException {
        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        mouse.addListener(MouseEventType.ON_CLICK, callback);

        mouse.click();
        mouse.doubleClick();
        // 当调用 addListener方法时，将名字为onClick的事件 ，注册到events事件管理中

        // 当调用click方法时，执行events中已经注册好的事件，未注册的实际不会触发
    }
}
