package com.raven.pattern.observer.advice;

import java.util.Observable;
import java.util.Observer;

/**
 * @PackageName: com.raven.pattern.observer.advice
 * @ClassName: Teacher
 * @Blame: raven
 * @Date: 2021-08-07 19:39
 * @Description:
 */
public class Teacher implements Observer {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        // 被观察者
        Coder coder = (Coder) o;
        System.out.println("=================");
        Question question = (Question) arg;
        System.out.println(name + "老师，你在" + coder.getName() + "中收到了"+question.getUsername()+"提的问题,\n"
        + "问题的内容是：\n" + question.getContent());
    }
}
