
```java
public class ConditionDemo{
    // 深入学习AQS Condition的await和signal，进行线程间通信
  
  /**
  * 将当前线程构建成节点加入condition队列(单向)释放锁后通过park挂起
 */
 // AbstractQueuedSynchronizer.class
  public final void await() throws InterruptedException {
        if (Thread.interrupted())
            {throw new InterruptedException();}
        // 将线程构建为node节点并加入condition队列中
       Node node = addConditionWaiter();
        // 释放当前对象锁获得的锁对象(只有释放锁，signal才有可能被执行)
        int savedState = fullyRelease(node);
        int interruptMode = 0;
        // 如果当前线程不在AQS队列中，则将节点挂起
        while (!isOnSyncQueue(node)) {
            LockSupport.park(this);
            // 之前调用await方法被阻塞的线程现在被唤醒后继续下面的逻辑
            if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                {break;}
        }
        // 如果当前线程争抢锁成功，并且是先执行signal 后被中断，则重新响应中断
        if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
            {interruptMode = REINTERRUPT;}
        if (node.nextWaiter != null) // clean up if cancelled
            // 如果线程被取消了 从condition队列中剔除该线程
          {unlinkCancelledWaiters();}
        if (interruptMode != 0)
        // 根据线程唤醒和中断的顺序，觉得进行抛出异常还是响应中断
            {reportInterruptAfterWait(interruptMode);}
    }
    
   //  ConditionObject.class
     private Node addConditionWaiter() {
               Node t = lastWaiter;
               // If lastWaiter is cancelled, clean out.
               if (t != null && t.waitStatus != Node.CONDITION) {
                   unlinkCancelledWaiters();
                   t = lastWaiter;
               }
               // 通过节点的构造方法将当前线程封装为节点，并设置阻塞状态(waitstatus)为CONDITION(-2)
               Node node = new Node(Thread.currentThread(), Node.CONDITION);
               // 用node构建一个单向链表  firstWaiter和lastWaiter都是该线程所封装的节点
               if (t == null)
                   {firstWaiter = node;}
               else
                   {t.nextWaiter = node;}
               lastWaiter = node;
               return node;
           }
           
         // 如果condition阻塞队列中有线程被取消，则遍历清除线程
        private void unlinkCancelledWaiters() {
               Node t = firstWaiter;
               Node trail = null;
               while (t != null) {
                   Node next = t.nextWaiter;
                   if (t.waitStatus != Node.CONDITION) {
                       t.nextWaiter = null;
                       if (trail == null)
                           firstWaiter = next;
                       else
                           trail.nextWaiter = next;
                       if (next == null)
                           lastWaiter = trail;
                   }
                   else
                       trail = t;
                   t = next;
               }
           }
        
   // AbstractQueuedSynchronizer.class
   // 释放锁
      final int fullyRelease(Node node) {
           boolean failed = true;
           try {
               // 获得重入次数 减去重入次数，释放锁
               int savedState = getState();
               if (release(savedState)) {
                   failed = false;
                   return savedState;
               } else {
                   throw new IllegalMonitorStateException();
               }
           } finally {
               if (failed)
                   node.waitStatus = Node.CANCELLED;
           }
       }    
       
       // 判断节点是否在AQS队列中
        final boolean isOnSyncQueue(Node node) {
       // 当节点状态为condition是，节点肯定在condition队列。当节点在AQS队列是,prev为空是头结点 
           if (node.waitStatus == Node.CONDITION || node.prev == null)
               return false;
           
           // node.next不为null 线程一定在AQS队列中
           if (node.next != null) // If has successor, it must be on queue
               return true;
           /*
            * node.prev can be non-null, but not yet on queue because
            * the CAS to place it on queue can fail. So we have to
            * traverse from tail to make sure it actually made it.  It
            * will always be near the tail in calls to this method, and
            * unless the CAS failed (which is unlikely), it will be
            * there, so we hardly ever traverse much.
            */
           return findNodeFromTail(node);
           }
           
           // 从后往前循环遍历 判断节点是否在AQS队列
           private boolean findNodeFromTail(Node node) {
                  Node t = tail;
                  for (;;) {
                      // 如果有节点==t 则证明在AQS队列
                      if (t == node)
                          return true;
                      // 一直遍历结束，直到遍历到头结点，依旧没有找到，则节点一定不在AQS队列中
                      if (t == null)
                          return false;
                      t = t.prev;
                  }
              } 
      
      // ****************************挂起线程后************************************************
      // 其他线程通过await释放锁后挂起。
      // 该线程执行signal唤醒被挂起的线程
      // ConditionObject.class
        public final void signal() {
            // 调用signal唤醒锁的线程一定不会是当前线程 
             if (!isHeldExclusively())
                 throw new IllegalMonitorStateException();
             Node first = firstWaiter;
             // 唤醒condition队列中的第一个线程
             if (first != null){
                 doSignal(first);
             }
         }
      
         // AbstractQueuedSynchronizer.class
        protected final boolean isHeldExclusively() {
           // While we must in general read state before owner,
           // we don't need to do so to check if current thread is owner
           return getExclusiveOwnerThread() == Thread.currentThread();
          }
          
          // ConditionObject.class
         private void doSignal(Node first) {
            do {
                if ( (firstWaiter = first.nextWaiter) == null){
                    lastWaiter = null;
                }
                // 将节点从condition队列中移除
                first.nextWaiter = null;
                // 添加成功后，执行do语句
            } while (!transferForSignal(first) &&
                     (first = firstWaiter) != null);
        } 
      
        // 如果当前节点在condition队列里，则将节点添加到AQS队列中
         final boolean transferForSignal(Node node) {
                /*
                 * If cannot change waitStatus, the node has been cancelled.
                 */
                // 正常情况下condition队列中的阶段状态一定为condition(-2) 如果没有设置成功，则证明该节点被中断取消
                if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
                    return false;
        
                /*
                 * Splice onto queue and try to set waitStatus of predecessor to
                 * indicate that thread is (probably) waiting. If cancelled or
                 * attempt to set waitStatus fails, wake up to resync (in which
                 * case the waitStatus can be transiently and harmlessly wrong).
                 */
                // 通过AQS的enq方法 将节点加入到AQS队列
                Node p = enq(node);
                // 获取之前的tail节点的状态
                int ws = p.waitStatus;
                // 如果之前的节点被取消或者之前的节点设置为SIGNAL状态失败，释放当前线程，让当前线程可以竞争锁
                if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
                    LockSupport.unpark(node.thread);
                return true;
            }
            
      // ****************************唤醒线程前************************************************
      
      
      // ****************************唤醒线程后************************************************
          
       // ConditionObject.class   
       // 校验线程在阻塞时是否又被中断过，如果有被中断过，则判断是先唤醒还是现中断
       private int checkInterruptWhileWaiting(Node node) {
          return Thread.interrupted() ?
              (transferAfterCancelledWait(node) ? THROW_IE : REINTERRUPT) :
              0;
      }     
           
       final boolean transferAfterCancelledWait(Node node) {
      // 如果cas成功，则证明是通过其他方式进行了中断，然后被唤醒，则将节点加入到AQS队列中
               if (compareAndSetWaitStatus(node, Node.CONDITION, 0)) {
                   enq(node);
                   return true;
               }
               /*
                * If we lost out to a signal(), then we can't proceed
                * until it finishes its enq().  Cancelling during an
                * incomplete transfer is both rare and transient, so just
                * spin.
                */
               while (!isOnSyncQueue(node))
                   Thread.yield();
               return false;
        }    
         
       private void unlinkCancelledWaiters() {
             Node t = firstWaiter;
             Node trail = null;
             while (t != null) {
                 Node next = t.nextWaiter;
                 if (t.waitStatus != Node.CONDITION) {
                     t.nextWaiter = null;
                     if (trail == null)
                         firstWaiter = next;
                     else
                         trail.nextWaiter = next;
                     if (next == null)
                         lastWaiter = trail;
                 }
                 else
                     trail = t;
                 t = next;
             }
         } 
         
         private void reportInterruptAfterWait(int interruptMode)
                throws InterruptedException {
                if (interruptMode == THROW_IE)
                    throw new InterruptedException();
                else if (interruptMode == REINTERRUPT)
                    selfInterrupt();
            }
            
            
            
    
        
 }   
 
 
 
 
```
