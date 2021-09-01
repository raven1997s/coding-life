package com.raven.multithreaded.concurrentutil.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.cyclicbarrier
 * @ClassName: CyclicBarrierTest
 * @Blame: raven
 * @Date: 2021-09-01 18:45
 * @Description: cyclicBarrierDemo 循环栅栏
 * 模拟文件上传  等待所有文件上传后，才执行指定逻辑
 */
public class CyclicBarrierTest extends Thread {

    public static void main(String[] args) {
        // 指定栅栏的个数 当有三个文件上传后，次进行数据分析
        // 第一个参数： 指定满足栅栏的个数
        // 第二个参数： 满足后所需要执行的逻辑
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierTest());
        new Thread(new DataImportThread("file1", cyclicBarrier)).start();
        new Thread(new DataImportThread("file2", cyclicBarrier)).start();
        new Thread(new DataImportThread("file3", cyclicBarrier)).start();
    }

    @Override
    public void run() {
        System.out.println("所有文件上传完毕，开始数据分析");
    }

    static class DataImportThread extends Thread {
        private String path;

        private CyclicBarrier cyclicBarrier;

        public DataImportThread(String path, CyclicBarrier cyclicBarrier) {
            this.path = path;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                // 文件上传后阻塞
                System.out.println("开始导入文件,已导入" + path + "的文件");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
