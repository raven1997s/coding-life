package com.raven.multithreaded.threadstatus;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.raven.multithreaded.threadstatus
 * @ClassName: ThreadStatusTest
 * @Blame: raven
 * @Date: 2021-08-12 21:44
 * @Description: 模拟线程不同的状态
 * 线程六大状态：
 * new (新建) runnable(可运行状态)[ready/running] terminated(终止状态)
 * waiting(等待状态) time_waiting(有时间限制的等待)  blocked(阻塞状态)
 */
public class ThreadStatusTest {

    public static void main(String[] args) {

        // Time_Waiting_Thread 模拟有时间限制的等待状态
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    // 当发生中断异常时，可通过catch中逻辑 终止线程
                    e.printStackTrace();
                }
            }
        }, "Time_Waiting_Thread").start();

        // Wait_Thread 模拟无时间限制的等待状态
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatusTest.class) {
                    try {
                        ThreadStatusTest.class.wait();
                    } catch (InterruptedException e) {
                        // 当发生中断异常时，可通过catch中逻辑 终止线程
                        e.printStackTrace();
                    }
                }
            }
        }, "Wait_Thread").start();

        // Blocked 模拟锁阻塞状态 01线程拿到锁后不释放，02阻塞
        new Thread(new BlockedThread(), "Blocked_01").start();
        new Thread(new BlockedThread(), "Blocked_02").start();


        // 测试方式：
        // 0.运行demo程序
        // 1.在target\classes\com\raven\multithreaded\threadstatus 目录下打开terminal命令窗口
        // 2.通过jps指令知道进行id
        // 3.通过jstack + 进程id值 查看线程运行状态 (jstack命令学习 https://blog.csdn.net/qq_41904194/article/details/104150372)
        // 4.查看线程状态
        /*
         * "Blocked_02" #17 prio=5 os_prio=0 tid=0x000000001f280800 nid=0x53f8 waiting for monitor entry [0x000000001fdbf000]
         *    java.lang.Thread.State: BLOCKED (on object monitor)
         *
         * "Blocked_01" #15 prio=5 os_prio=0 tid=0x000000001f280000 nid=0x5d10 waiting on condition [0x000000001fcbf000]
         *    java.lang.Thread.State: TIMED_WAITING (sleeping)
         *
         * "Wait_Thread" #13 prio=5 os_prio=0 tid=0x000000001f27f000 nid=0x475c in Object.wait() [0x000000001fbbe000]
         *    java.lang.Thread.State: WAITING (on object monitor)
         *
         * "Time_Waiting_Thread" #12 prio=5 os_prio=0 tid=0x000000001f27c800 nid=0x2be0 waiting on condition [0x000000001fabe000]
         *    java.lang.Thread.State: TIMED_WAITING (sleeping)
         */
    }

    static class BlockedThread extends Thread {
        @Override
        public void run() {
            // 设置synchronized 模拟死锁场景
            synchronized (BlockedThread.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
