```java
public class CyclicBarrierDemo{
    
    //  cyclicBarrier.await();
    
    // CyclicBarrier.class
      public int await() throws InterruptedException, BrokenBarrierException {
            try {
                return dowait(false, 0L);
            } catch (TimeoutException toe) {
                throw new Error(toe); // cannot happen
            }
      }
      
     // 执行阻塞 
     // 基于Lock锁 以及condition 完成阻塞操作
     private int dowait(boolean timed, long nanos) throws InterruptedException, BrokenBarrierException,TimeoutException {
         final ReentrantLock lock = this.lock;
         lock.lock();
         try {
             final Generation g = generation;
     
             if (g.broken)
                 throw new BrokenBarrierException();
     
             // 如果线程有被中断过，则终止barrier 并抛出异常
             if (Thread.interrupted()) {
                 breakBarrier();
                 throw new InterruptedException();
             }
     
             // count为构建栅栏时指定的突破栅栏需要的次数
             int index = --count;
             if (index == 0) {  // tripped
                 boolean ranAction = false;
                 try {
                     // 如果构造参数有传突破栅栏后的命令，则执行命令
                     final Runnable command = barrierCommand;
                     if (command != null)
                         command.run();
                     ranAction = true;
                     // 重置栅栏参数
                     nextGeneration();
                     return 0;
                 } finally {
                     if (!ranAction)
                         breakBarrier();
                 }
             }
     
             // loop until tripped, broken, interrupted, or timed out
             for (;;) {
                 try {
                     if (!timed)
                         // 阻塞线程
                         // 基于condition的await进行阻塞挂起线程
                         trip.await();
                     else if (nanos > 0L)
                        // 延时阻塞
                         nanos = trip.awaitNanos(nanos);
                 } catch (InterruptedException ie) {
                     if (g == generation && ! g.broken) {
                         breakBarrier();
                         throw ie;
                     } else {
                         // We're about to finish waiting even if we had not
                         // been interrupted, so this interrupt is deemed to
                         // "belong" to subsequent execution.
                         Thread.currentThread().interrupt();
                     }
                 }
     
                 if (g.broken)
                     throw new BrokenBarrierException();
     
                 // 当突破栅栏后 会构建新的generation 此时 g != generation 终止循环
                 if (g != generation)
                     return index;
     
                 if (timed && nanos <= 0L) {
                     breakBarrier();
                     throw new TimeoutException();
                 }
             }
         } finally {
             lock.unlock();
         }
     }
     
      private void nextGeneration() {
             // signal completion of last generation
             trip.signalAll();
             // set up next generation
             count = parties;
             generation = new Generation();
         }
}
```