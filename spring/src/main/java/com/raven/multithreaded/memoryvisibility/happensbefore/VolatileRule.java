package com.raven.multithreaded.memoryvisibility.happensbefore;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility.happensbefore
 * @ClassName: VolatileRule
 * @Blame: raven
 * @Date: 2021-08-16 21:16
 * @Description: happens-before规则 - volatile规则
 * 被volatile关键字修饰的变量 在被修改的时候都会有内存屏障指令(OrderAccess::storeload)
 * 从而确保内存的可见性
 */
public class VolatileRule {

    volatile static int num = 0;
    volatile static boolean flag = false;

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

    public static void main(String[] args) throws InterruptedException {
        System.out.println("before:" + num);
        // thread线程修改 num 和 flag的值
        new Thread(() -> {
            writer();
        }).start();

        // 主线程读取，仍然可以读取到修改后的值
        Thread.sleep(1000);
        reader();
        System.out.println("after:" + num);
    }
}
