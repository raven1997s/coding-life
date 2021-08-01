package com.raven.pattern.delegate.simple;

/**
 * @PackageName: com.raven.pattern.delegate.simple
 * @ClassName: SimpleTest
 * @Blame: raven
 * @Date: 2021-08-01 14:07
 * @Description: 委派模式简单案例 模仿BOSS下达指令给leader BOSS不关心到底具体由谁再干活 只需要让leader完成任务即可
 * leader不直接自己干活，而根据任务的特点 ，调用不同的employee 进行执行任务
 */
public class SimpleTest {

    public static void main(String[] args) {
        Boss boss = new Boss();
        boss.execute("JAVA",new Leader());
        boss.execute("GO",new Leader());

    }
}
