package com.raven.pattern.observer.advice;

import lombok.Data;

import java.util.Objects;
import java.util.Observable;

/**
 * @PackageName: com.raven.pattern.observer.advice
 * @ClassName: Coder
 * @Blame: raven
 * @Date: 2021-08-07 19:30
 * @Description: 被观察者，通过继承Observable得到实现 是JDK提供的一种观察者的实现方式
 */
@Data
public class Coder extends Observable {

    private String name = "Coder生态圈";
    private static volatile Coder coder = null;

    private Coder() {
    }

    public static Coder getInstance() {
        if (Objects.isNull(coder)) {
            synchronized (Coder.class) {
                if (Objects.isNull(coder)) {
                    coder = new Coder();
                }
            }
        }
        return coder;
    }

    public void publishQuestion(Question question) {
        System.out.println(question.getUsername() + "在" + name + "提了一个问题");
        // 通过该访问提交问题
        setChanged();
        // 通知所有观察该类的观察者们 将问题传递
        notifyObservers(question);
    }

}
