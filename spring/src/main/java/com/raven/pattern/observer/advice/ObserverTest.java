package com.raven.pattern.observer.advice;

/**
 * @PackageName: com.raven.pattern.observer.advice
 * @ClassName: ObserverTest
 * @Blame: raven
 * @Date: 2021-08-07 19:52
 * @Description: JDK观察者模式 demo测试类
 */
public class ObserverTest {
    public static void main(String[] args) {

        // 获取一个被观察coder
        Coder coder = Coder.getInstance();
        Question question = new Question();
        question.setContent("如何学好设计模式？");
        question.setUsername("小王");

        // 创建一个观察者teacher
        Teacher teacher = new Teacher("raven");

        // 将观察者和被观察者绑定
        coder.addObserver(teacher);

        // 被观察将问题提交
        coder.publishQuestion(question);
    }
}
