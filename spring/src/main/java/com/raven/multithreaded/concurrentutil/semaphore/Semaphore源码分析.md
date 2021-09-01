```java
public class SemaphoreDemo{
    
    //  semaphore.acquire();
    
    // 尝试获取令牌
     public void acquire() throws InterruptedException {
            // 获取共享锁对象
         sync.acquireSharedInterruptibly(1);
     }
     
     public final void acquireSharedInterruptibly(int arg)
             throws InterruptedException {
         if (Thread.interrupted())
             throw new InterruptedException();
         // 尝试获取共享锁，获取令牌，如果没有令牌了，则进行阻塞
         if (tryAcquireShared(arg) < 0){
             doAcquireSharedInterruptibly(arg);
             }
     }
     
     // 构建共享式的节点并加入AQS队列 阻塞挂起线程
       private void doAcquireSharedInterruptibly(int arg) throws InterruptedException {
       // 构建共享式的节点，并加入AQS队列 返回节点
             final Node node = addWaiter(Node.SHARED);
             boolean failed = true;
             try {
                 for (;;) {
                     // 获取上一个节点
                     final Node p = node.predecessor();
                     if (p == head) {
                         // 当节点线程被唤醒后，尝试获取锁对象 获取令牌。
                         // 如果还有令牌，则传播的唤醒下一个线程
                         int r = tryAcquireShared(arg);
                         if (r >= 0) {
                             // 设置head 传播的方式唤醒其他线程
                             setHeadAndPropagate(node, r);
                             p.next = null; // help GC
                             failed = false;
                             return;
                         }
                     }
                     // 挂起线程
                     if (shouldParkAfterFailedAcquire(p, node) &&
                         parkAndCheckInterrupt())
                         throw new InterruptedException();
                 }
             } finally {
                 if (failed)
                     cancelAcquire(node);
             }
         }
     // AQS.class
     // 调用AQS获取共享锁对象，获取令牌
      protected int tryAcquireShared(int arg) {
             throw new UnsupportedOperationException();
         }
         
      // NonfairSync.class
      // 尝试获取共享锁 返回还剩余的令牌个数
      protected int tryAcquireShared(int acquires) {
                  return nonfairTryAcquireShared(acquires);
              }
      // Sync.class
      // 不公平的尝试获取共享锁
      final int nonfairTryAcquireShared(int acquires) {
           for (;;) {
               // 获取令牌个数
               int available = getState();
               int remaining = available - acquires;
               // 返回还剩下的令牌个数
               if (remaining < 0 ||
                   compareAndSetState(available, remaining))
                   return remaining;
           }
       }
       
     // semaphore.release();
     // Semaphore.class  
     public void release() {
              sync.releaseShared(1);
     } 
       
     // AQS.clas
     public final boolean releaseShared(int arg) {
             if (tryReleaseShared(arg)) {
                 doReleaseShared();
                 return true;
             }
             return false;
     }
     
     // 尝试释放共享锁 -> 设置state的值
     protected final boolean tryReleaseShared(int releases) {
          for (;;) {
              int current = getState();
              int next = current + releases;
              if (next < current) // overflow
                  throw new Error("Maximum permit count exceeded");
              // 通过cas设置 当前的state
              if (compareAndSetState(current, next))
                  return true;
          }
      }
      
      // 执行释放共享锁 唤醒挂起的线程
      private void doReleaseShared() {
          for (;;) {
              Node h = head;
              if (h != null && h != tail) {
                  int ws = h.waitStatus;
                  if (ws == Node.SIGNAL) {
                      if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                          continue;            // loop to recheck cases
                      unparkSuccessor(h);
                  }
                  else if (ws == 0 &&
                           !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                      continue;                // loop on failed CAS
              }
              if (h == head)                   // loop if head changed
                  break;
          }
      }
}
```