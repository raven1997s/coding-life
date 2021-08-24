package com.raven.multithreaded.thoroughlock;

/**
 * @PackageName: com.raven.multithreaded.thoroughlock
 * @ClassName: ReentrantLockTest
 * @Blame: raven
 * @Date: 2021-08-24 20:57
 * @Description: 重入锁
 */
public class ReentrantLockTest {

    /**
     * 锁对象是ReentrantLockTest 对象 线程调用获取锁对象
     */
    public synchronized void demo() {
        System.out.println("demo");
        demo2();
    }

    public void demo2(){
        // 锁对象是ReentrantLockTest 对象
        // 再次获取锁对象
        // 增加重入次数
        synchronized (this){
            System.out.println("demo2");
        } // 减少重入次数
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        test.demo();
    }
}
