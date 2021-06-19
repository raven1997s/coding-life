package com.raven.pattern.singleton.lazy;

/**
 * @PackageName: com.raven.pattern.singleton.lazy
 * @ClassName: ExecutorSingleton
 * @Blame: raven
 * @Date: 2021-06-19 22:35
 * @Description:
 */
public class ExecutorSingleton implements Runnable {

    @Override
    public void run() {
        LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }
}
