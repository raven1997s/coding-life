package com.raven.multithreaded.memoryvisibility.happensbefore;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility.happensbefore
 * @ClassName: SyncRule
 * @Blame: raven
 * @Date: 2021-08-16 21:49
 * @Description: happens-before规则 - synchronized锁
 * 加锁可以满足 happens-before规则 ，确保数据的内存可见性
 */
public class SyncRule {
    public void demo(){
        synchronized (this){
            // do sth
        }
    }
}
