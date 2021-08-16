package com.raven.multithreaded.memoryvisibility.happensbefore;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility.happensbefore
 * @ClassName: SingleThreadSequenceRule
 * @Blame: raven
 * @Date: 2021-08-16 21:07
 * @Description: happens-before规则 - 程序的顺序规则
 * 单线程下程序一定顺序执行
 * 可以保障内存的可见性
 */
public class SingleThreadSequenceRule {

    static int num = 0;
    static boolean flag = false;

    public static void writer() {
        // 1
        num = 10;
        // 2
        flag = true;
        // 1 happens-before 2
    }

    public static void reader() {
        // 3
        if (flag) {
            // 4
            num = 100;
        }
        // 3 happens-before 4
    }

    public static void main(String[] args) {
        writer();
        reader();
        System.out.println(num);
    }
}
