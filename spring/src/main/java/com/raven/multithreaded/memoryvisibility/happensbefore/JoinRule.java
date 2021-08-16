package com.raven.multithreaded.memoryvisibility.happensbefore;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility.happensbefore
 * @ClassName: JoinRule
 * @Blame: raven
 * @Date: 2021-08-16 21:43
 * @Description: happens-before规则 - join规则
 */
public class JoinRule {
    static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            num = 100;
        });
        thread.start();
        // join会阻塞主线程 底层是基于wait/notify通信的
        // 等到阻塞释放，获取thread线程的执行结果
        // join会主动简历一个 happens-before规则
        thread.join();
        System.out.println(num);
    }
}
