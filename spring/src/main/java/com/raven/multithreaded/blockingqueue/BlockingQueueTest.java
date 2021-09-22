package com.raven.multithreaded.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @PackageName: com.raven.multithreaded.blockingqueue
 * @ClassName: BlockingQueueTest
 * @Blame: raven
 * @Date: 2021-09-22 15:31
 * @Description:
 */
public class BlockingQueueTest {

    /**
     * 构建一个阻塞队列
     */
    ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10);

    {
        init();
    }

    /**
     * 不间断的从队列中获取元素
     */
    private void init() {
        new Thread(() -> {
           while (true){
               try {
                   // 阻塞式的获取队列中的元素
                   String data = abq.take();
                   System.out.println("receive： " + data);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();
    }

    /**
     * 将元素放入队列中
     *
     * @param data
     * @throws InterruptedException
     */
    public void addData(String data) throws InterruptedException {
        abq.add(data);
        System.out.println("sendData: " + data);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueTest bqt = new BlockingQueueTest();
        for (int i = 0; i < 10; i++) {
            bqt.addData(" data" + i);
        }
    }
}
