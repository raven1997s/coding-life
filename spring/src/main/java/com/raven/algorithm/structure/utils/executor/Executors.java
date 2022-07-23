package com.raven.algorithm.structure.utils.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2022/7/23 09:58
 *
 * @author raven
 */
public class Executors {

    private static ArrayBlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<>(2000);

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 32, 5, TimeUnit.MINUTES, blockQueue,
            NamedThreadFactory.newNamedThreadFactory("myThread-"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}