```java
public class CountDownLatchDemo{
    
    // countDownLatch.await();
    
    // Sync.class
     public void await() throws InterruptedException {
            // 获取共享锁，并且是可中断的
            sync.acquireSharedInterruptibly(1);
      }
      
     // AQS
     public final void acquireSharedInterruptibly(int arg) throws InterruptedException {
            // 如果线程有被中断过，抛出异常
             if (Thread.interrupted())
                 throw new InterruptedException();
             
             // 尝试获取共享锁 
             if (tryAcquireShared(arg) < 0){
                 // 初始化后，因为为调用countDown 所以需要执行获取可中断共享锁
                 doAcquireSharedInterruptibly(arg);
             }
     }
     
     // 尝试获取共享锁 
     protected int tryAcquireShared(int acquires) {
         // state代表计数的值，当调用countDown后state减小
                 return (getState() == 0) ? 1 : -1;
     }
     
     private void doAcquireSharedInterruptibly(int arg) throws InterruptedException {
            // 使用共享的方式 将当前线程封装为节点构建AQS队列，并将节点添加到AQS队列中 
             final Node node = addWaiter(Node.SHARED);
             boolean failed = true;
             try {
                 for (;;) {
                     // 获取当前节点的上一个节点
                     final Node p = node.predecessor();
                     if (p == head) {
                         int r = tryAcquireShared(arg);
                         if (r >= 0) {
                             setHeadAndPropagate(node, r);
                             p.next = null; // help GC
                             failed = false;
                             return;
                         }
                     }
                     // 通过自旋的方式获取共享锁 然后挂起当前线程 
                     // 如果获取锁后当前线程有被中断过，则抛出中断异常响应中断
                     if (shouldParkAfterFailedAcquire(p, node) &&
                         parkAndCheckInterrupt())
                         throw new InterruptedException();
                 }
             } finally {
                 if (failed)
                     cancelAcquire(node);
             }
     }
     
     //  countDownLatch.countDown();
     // sync.class
     // 释放共享锁
      public void countDown() {
             sync.releaseShared(1);
     }
         
      public final boolean releaseShared(int arg) {
               // 当多个线程执行countDown 将state减为0后 执行释放锁的逻辑 
           if (tryReleaseShared(arg)) {
               // 释放锁
               doReleaseShared();
               return true;
           }
           return false;
       }
       
       // 通过自旋的方式 cas的设置state的值 每次为state-1
       protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (;;) {
                int c = getState();
                if (c == 0){
                    return false;
                }
                int nextc = c-1;
                if (compareAndSetState(c, nextc)){
                    return nextc == 0;
                }
            }
        } 
        
        private void doReleaseShared() {
          for (;;) {
              Node h = head;
              if (h != null && h != tail) {
                  int ws = h.waitStatus;
                  // 
                  if (ws == Node.SIGNAL) {
                      // 因为通过await后 所有线程状态都为-1
                      // 所以cas失败后，不进行唤醒挂起的线程
                      if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                          continue;            // loop to recheck cases
                      // 唤醒挂起的线程
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
        
//        ******************************线程被唤醒后****************************************************
/**
*  
*  线程被唤醒后，释放了共享锁，doAcquireSharedInterruptibly继续执行自旋的逻辑
*  
                       final Node p = node.predecessor();
                       if (p == head) {
                       // 此时已经通过countDown将state减为0
                       // r=1  r>0
                           int r = tryAcquireShared(arg);
                           if (r >= 0) {
                               setHeadAndPropagate(node, r);
                               p.next = null; // help GC
                               failed = false;
                               return;
                           }
                       }
*/

        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }
        
        // 传播的将当前线程设置为head 并唤醒下一个节点，传播的唤醒 释放共享锁
         private void setHeadAndPropagate(Node node, int propagate) {
                Node h = head; // Record old head for check below
                setHead(node);
                
                if (propagate > 0 || h == null || h.waitStatus < 0 ||
                    (h = head) == null || h.waitStatus < 0) {
                    // 获取下一个节点
                    Node s = node.next;
                    // 传播的唤醒下一个节点，进行释放锁
                    if (s == null || s.isShared())
                        doReleaseShared();
                }
            }
}

```