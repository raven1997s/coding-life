package com.raven.multithreaded.concurrentutil.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.semaphore
 * @ClassName: SemaphoreTest
 * @Blame: raven
 * @Date: 2021-09-01 16:51
 * @Description: semaphore demo 常用于限流使用
 *
 * 模拟停车场停车案例
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        // 限流 。只有五个停车位
        // 通过构造参数觉得限流构成的共享锁是共平的还是非公平的 默认为非公平锁
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            new Thread(new Car(i + 1, semaphore)).start();
        }

    }
   static class Car extends Thread{
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取令牌，如果能拿到令牌则执行下面的逻辑，如果不能拿到则阻塞
                //  if (hasQueuedPredecessors()) 如果是公平锁，发现AQS队列有其他线程，则不会再去争取共享锁
                semaphore.acquire();
                System.out.println("第" + num + "辆车开进来了");
                Thread.sleep(2000);
                System.out.println("第" + num + "辆车开走了");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
