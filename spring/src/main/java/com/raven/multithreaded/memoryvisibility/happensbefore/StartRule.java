package com.raven.multithreaded.memoryvisibility.happensbefore;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility.happensbefore
 * @ClassName: StartRule
 * @Blame: raven
 * @Date: 2021-08-16 21:27
 * @Description: happens-before规则 - 启动规则
 * 在线程调用start方法前，其他线程的修改是对该线程可见的
 */
public class StartRule {
    static int num = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(num);
        });
        num = 100;
        thread.start();

    }
}
