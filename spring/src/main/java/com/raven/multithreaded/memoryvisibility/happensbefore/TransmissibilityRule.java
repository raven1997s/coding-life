package com.raven.multithreaded.memoryvisibility.happensbefore;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility.happensbefore
 * @ClassName: TransmissibilityRule
 * @Blame: raven
 * @Date: 2021-08-16 21:24
 * @Description: happens-before规则 - 传递性规则
 * 线程间的happens-before依赖会进行传递
 * 即：1 happens-before 2
 * 2 happens-before 3
 * 则 1 happens-before 3
 */
public class TransmissibilityRule {
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
