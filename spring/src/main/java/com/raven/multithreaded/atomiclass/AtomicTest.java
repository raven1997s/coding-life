package com.raven.multithreaded.atomiclass;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PackageName: com.raven.multithreaded.atomiclass
 * @ClassName: AtomicTest
 * @Blame: raven
 * @Date: 2021-09-22 16:13
 * @Description:
 */
public class AtomicTest {

    static int count;

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static void incr(){
        count ++;
        atomicInteger.incrementAndGet();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
           new Thread(AtomicTest::incr).start();
        }

        System.out.println(count);
        System.out.println(atomicInteger.get());
    }
}
