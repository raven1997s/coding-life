package com.raven.data_structures_and_algorithms.structure.utils.executor;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * date: 2022/7/23 10:01
 *
 * @author raven
 */

public class NamedThreadFactory implements ThreadFactory {
    public static final String COMMON_PREFIX = "pool-";
    private static final AtomicInteger threadPoolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private static final String NAME_PATTERN = "%s-%d-thread";
    private final String threadNamePrefix;

    private NamedThreadFactory(String threadNamePrefix) {
        SecurityManager s = System.getSecurityManager();
        this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.threadNamePrefix = String.format(Locale.ROOT, "%s-%d-thread", checkPrefix(threadNamePrefix), threadPoolNumber.getAndIncrement());
    }

    public static NamedThreadFactory newNamedThreadFactory(String threadNamePrefix) {
        return new NamedThreadFactory("pool-" + threadNamePrefix);
    }

    private static String checkPrefix(String prefix) {
        return prefix != null && prefix.length() != 0 ? prefix : "NamedPool";
    }

    public Thread newThread(Runnable r) {
        String threadName = String.format(Locale.ROOT, "%s-%d", this.threadNamePrefix, this.threadNumber.getAndIncrement());
        Thread t = new Thread(this.group, r, threadName, 0L);
        t.setDaemon(false);
        t.setPriority(5);
        return t;
    }
}