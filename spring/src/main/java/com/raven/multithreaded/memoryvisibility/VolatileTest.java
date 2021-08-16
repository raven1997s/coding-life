package com.raven.multithreaded.memoryvisibility;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility
 * @ClassName: VolatileTest
 * @Blame: raven
 * @Date: 2021-08-16 20:53
 * @Description: 使用volatile关键字保证内存可见性
 */
public class VolatileTest {
    private volatile static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            // thread线程 正常情况下是会一直输出字符串，
            // 因为flag被volatile修饰，能够确保内存可见性，
            // 所以当主线程修改flag后，会读取flag的值，停止输出跳出循环，终止线程。
            while (!flag) {
                System.out.println("thread running");
            }
        });
        thread.start();
        Thread.sleep(1000);
        flag = true;
    }
}
