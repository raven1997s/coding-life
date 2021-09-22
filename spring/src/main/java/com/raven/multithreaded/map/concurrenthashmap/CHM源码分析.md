```java
public class CHMTest{
    
    // 插入数据
    public V put(K key, V value) {
        return putVal(key, value, false);
    }
    
    // 真正执行插入逻辑的diamante
    final V putVal(K key, V value, boolean onlyIfAbsent) {
            if (key == null || value == null) throw new NullPointerException();
            // 将hash值再次计算，更散列的分布
            int hash = spread(key.hashCode());
            int binCount = 0;
            for (Node<K,V>[] tab = table;;) {
                Node<K,V> f; int n, i, fh;
                // 如果数组为空 或者没有元素初始化数组
                if (tab == null || (n = tab.length) == 0)
                    // 初始化数组
                    tab = initTable();
                // 从数组中获取指定索引下标的元素，如果该位置没有数据，通过cas设置数值
                else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                    if (casTabAt(tab, i, null,
                                 new Node<K,V>(hash, key, value, null)))
                        break;                   // no lock when adding to empty bin
                }
                // 如果需要扩容 则复制转移数据到新的数据结构中
                else if ((fh = f.hash) == MOVED)
                    tab = helpTransfer(tab, f);
                else {
                    V oldVal = null;
                    synchronized (f) {
                        if (tabAt(tab, i) == f) {
                            if (fh >= 0) {
                                binCount = 1;
                                for (Node<K,V> e = f;; ++binCount) {
                                    K ek;
                                    if (e.hash == hash &&
                                        ((ek = e.key) == key ||
                                         (ek != null && key.equals(ek)))) {
                                        oldVal = e.val;
                                        if (!onlyIfAbsent)
                                            e.val = value;
                                        break;
                                    }
                                    Node<K,V> pred = e;
                                    if ((e = e.next) == null) {
                                        pred.next = new Node<K,V>(hash, key,
                                                                  value, null);
                                        break;
                                    }
                                }
                            }
                            else if (f instanceof TreeBin) {
                                Node<K,V> p;
                                binCount = 2;
                                if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                               value)) != null) {
                                    oldVal = p.val;
                                    if (!onlyIfAbsent)
                                        p.val = value;
                                }
                            }
                        }
                    }
                    if (binCount != 0) {
                        if (binCount >= TREEIFY_THRESHOLD)
                            treeifyBin(tab, i);
                        if (oldVal != null)
                            return oldVal;
                        break;
                    }
                }
            }
            addCount(1L, binCount);
            return null;
        }
        
     /**
      * Initializes table, using the size recorded in sizeCtl.
      * 初始化数组，记录线程扩容的阈值
      */
    private final Node<K,V>[] initTable() {
         Node<K,V>[] tab; int sc;
         while ((tab = table) == null || tab.length == 0) {
             // 默认sizeCtl为0 
             // 当有线程在执行初始化操作时为-1.线程礼让，让其他线程执行
             // 通过sizectl作为一个标记未，来保证线程安全问题
             if ((sc = sizeCtl) < 0)
                 Thread.yield(); // lost initialization race; just spin
             // CAS成功后 进行线程初始化将
             else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                 try {
                     if ((tab = table) == null || tab.length == 0) {
                         int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                         @SuppressWarnings("unchecked")
                         Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                         table = tab = nt;
                         // [16 - （16 = 10000 -> 000100 = 4）]  = 12
                         // 数组扩容的阈值为12
                         sc = n - (n >>> 2);
                     }
                 } finally {
                     sizeCtl = sc;
                 }
                 break;
             }
         }
         return tab;
     }    
     
     
    private final void addCount(long x, int check) {
        CounterCell[] as; long b, s;
    // 当CounterCell数组构建完成 或者尝试CAS设置Map的baseCount失败 就通过CounterCell数组分而治之设置value属性值
       if ((as = counterCells) != null || !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) {
           CounterCell a; long v; int m;
           boolean uncontended = true;
           if (as == null || (m = as.length - 1) < 0 ||
           // 通过ThreadLocalRandom.getProbe()生成一个线程安全的随机数
           // 通过& 运算确保结果集落在 m (as.length -1)中，然后获取数组的元素
               (a = as[ThreadLocalRandom.getProbe() & m]) == null ||
           // 如果CounterCell已经存在，可以直接通过cas设置value的值
               !(uncontended = U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
               fullAddCount(x, uncontended);
               return;
           }
           if (check <= 1)
               return;
           // 计算所有数组的length之和 即为map集合的size
           s = sumCount();
       }
       
       // 校验数组是否需要扩容
       // 链表长度大于等于0才考虑是否需要扩容
       // CHM的扩容是可以多个线程并行的扩容的(并行的copy数据)
       if (check >= 0) {
           Node<K,V>[] tab, nt; int n, sc;
           // 当数组长度>=阈值 并且集合节点数组不为空 集合节点数组长度小于Max int时 扩容
           while (s >= (long)(sc = sizeCtl) && (tab = table) != null &&
                  (n = tab.length) < MAXIMUM_CAPACITY) {
               int rs = resizeStamp(n);
               // sc一定小于0 表示有线程在扩容 
               if (sc < 0) {
                   if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                       sc == rs + MAX_RESIZERS || (nt = nextTable) == null ||
                       transferIndex <= 0)
                       break;
                   if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
                       transfer(tab, nt);
               }
               // 如果没有线程在扩容 通过cas设置sizeCtl的值为非-1的负数，用于描述有几个线程在扩容
               // rs = resizeStamp(16) = 32795 = 0000 0000 0000 0000 1000 0000 0001 1011
               // rs << RESIZE_STAMP_SHIFT  = 1000 0000 0001 1011 0000 0000 0000 0000
               // (rs << RESIZE_STAMP_SHIFT) + 2 = 1000 0000 0001 1011 0000 0000 0000 0010  在标记位1000 0000 0001 1011的长度周期中 有1个线程在扩容
               // +2的原因，最后是将结果设置给SIZECTL 而SIZECTL = -1 时已经有了其他的含义 所以+2来标识当前有一个线程在扩容
               // 高16位代表扩容的标记(扩容戳) ：1000 0000 0001 1011
               // 低16位代表当前扩容的线程数 ： 0000 0000 0000 0010 
               else if (U.compareAndSwapInt(this, SIZECTL, sc, (rs << RESIZE_STAMP_SHIFT) + 2))
                   // 扩容核心逻辑 1.扩大数组的长度 2.数据迁移
                   transfer(tab, null);
               s = sumCount();
           }
       }
    } 
    
    
    /**
    * 
    * @param n 数组的长度
    * @return  resizeStamp(16) = 32795
    * 
    * 0000 0000 0000 0000 1000 0000 0001 1011
    */
     static final int resizeStamp(int n) {
         // Integer.numberOfLeadingZeros(n) 返回最高位无符号位前的0的个数
         // n = 16 = 10000 Integer.numberOfLeadingZeros(16)   
         return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
     }
        
    final Node<K,V>[] helpTransfer(Node<K,V>[] tab, Node<K,V> f) {
         Node<K,V>[] nextTab; int sc;
         if (tab != null && (f instanceof ForwardingNode) &&
             (nextTab = ((ForwardingNode<K,V>)f).nextTable) != null) {
             int rs = resizeStamp(tab.length);
             while (nextTab == nextTable && table == tab &&
                    (sc = sizeCtl) < 0) {
                 if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                     sc == rs + MAX_RESIZERS || transferIndex <= 0)
                     break;
                 if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1)) {
                     transfer(tab, nextTab);
                     break;
                 }
             }
             return nextTab;
         }
         return table;
    }
    
    
    /**
    * 1.扩大数组的长度 2.数据迁移
    * @param tab
    * @param nextTab
    */
    private final void transfer(Node<K,V>[] tab, Node<K,V>[] nextTab) {
            int n = tab.length, stride;
            // 让每一段CPU去执行一段数据的扩容，每一个CPU可以处理16个长度的数组
            if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
                stride = MIN_TRANSFER_STRIDE; // subdivide range
                
             // 构建数组迁移需要的新的数据结构   
            if (nextTab == null) {            // initiating
                try {
                    @SuppressWarnings("unchecked")
                    // 构建一个当前容量二倍的数组 
                    Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n << 1];
                    nextTab = nt;
                } catch (Throwable ex) {      // try to cope with OOME
                    sizeCtl = Integer.MAX_VALUE;
                    return;
                }
                nextTable = nextTab;
                // transferIndex = n 等于原本数组长度的2倍
                transferIndex = n;
            }
            int nextn = nextTab.length;
            ForwardingNode<K,V> fwd = new ForwardingNode<K,V>(nextTab);
            boolean advance = true;
            boolean finishing = false; // to ensure sweep before committing nextTab
            
            for (int i = 0, bound = 0;;) {
                Node<K,V> f; int fh;
            // 设置分工的区间    
            // 划分每个线程负责的 转移数据的范围 【index ，bound】
                while (advance) {
                    int nextIndex, nextBound;
                    if (--i >= bound || finishing)
                        advance = false;
                    else if ((nextIndex = transferIndex) <= 0) {
                        i = -1;
                        advance = false;
                    }
                    else if (U.compareAndSwapInt
                             (this, TRANSFERINDEX, nextIndex,
                              nextBound = (nextIndex > stride ?
                                           nextIndex - stride : 0))) {
                        // 例： 数组length为16扩容为32 
                        // 第一个线程进来时 nextIndex = 32 stride = 16  nextBound = 16 bound = 16 i = 31
                        // 第二个线程进来时 bound = 0 i = 15
                        bound = nextBound;
                        i = nextIndex - 1;
                        advance = false;
                    }
                }
                if (i < 0 || i >= n || i + n >= nextn) {
                    int sc;
                    if (finishing) {
                        nextTable = null;
                        table = nextTab;
                        sizeCtl = (n << 1) - (n >>> 1);
                        return;
                    }
                    // 线程处理完毕后，修改sizeCtl 的值，当前处理扩容的线程数量减少一个
                    if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
                        if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
                            return;
                        finishing = advance = true;
                        i = n; // recheck before commit
                    }
                }
                else if ((f = tabAt(tab, i)) == null)
                    advance = casTabAt(tab, i, null, fwd);
                else if ((fh = f.hash) == MOVED)
                    advance = true; // already processed
                else {
                    // 数据迁移
                    // 主要做俩个事情  
                    // 1.将链表中的数据分为ln地位链 hn高位链
                    // 2.将数据放入新的数组列表下
                    synchronized (f) {
                        if (tabAt(tab, i) == f) {
                            Node<K,V> ln, hn;
                            if (fh >= 0) {
                                // 把当前链表进行分类 ln
                                int runBit = fh & n;
                                Node<K,V> lastRun = f;
                                for (Node<K,V> p = f.next; p != null; p = p.next) {
                                    int b = p.hash & n;
                                    if (b != runBit) {
                                        runBit = b;
                                        lastRun = p;
                                    }
                                }
                                if (runBit == 0) {
                                    ln = lastRun;
                                    hn = null;
                                }
                                else {
                                    hn = lastRun;
                                    ln = null;
                                }
                                // 将链表分为不同的高低链路
                                for (Node<K,V> p = f; p != lastRun; p = p.next) {
                                    int ph = p.hash; K pk = p.key; V pv = p.val;
                                    if ((ph & n) == 0)
                                        ln = new Node<K,V>(ph, pk, pv, ln);
                                    else
                                        hn = new Node<K,V>(ph, pk, pv, hn);
                                }
                                // 低位链保持位置不动
                                setTabAt(nextTab, i, ln);
                                // 高位链需要移动n长度的位置
                                setTabAt(nextTab, i + n, hn);
                                setTabAt(tab, i, fwd);
                                advance = true;
                            }
                            // 红黑树的扩容逻辑
                            else if (f instanceof TreeBin) {
                                TreeBin<K,V> t = (TreeBin<K,V>)f;
                                TreeNode<K,V> lo = null, loTail = null;
                                TreeNode<K,V> hi = null, hiTail = null;
                                int lc = 0, hc = 0;
                                for (Node<K,V> e = t.first; e != null; e = e.next) {
                                    int h = e.hash;
                                    TreeNode<K,V> p = new TreeNode<K,V>
                                        (h, e.key, e.val, null, null);
                                    if ((h & n) == 0) {
                                        if ((p.prev = loTail) == null)
                                            lo = p;
                                        else
                                            loTail.next = p;
                                        loTail = p;
                                        ++lc;
                                    }
                                    else {
                                        if ((p.prev = hiTail) == null)
                                            hi = p;
                                        else
                                            hiTail.next = p;
                                        hiTail = p;
                                        ++hc;
                                    }
                                }
                                ln = (lc <= UNTREEIFY_THRESHOLD) ? untreeify(lo) :
                                    (hc != 0) ? new TreeBin<K,V>(lo) : t;
                                hn = (hc <= UNTREEIFY_THRESHOLD) ? untreeify(hi) :
                                    (lc != 0) ? new TreeBin<K,V>(hi) : t;
                                setTabAt(nextTab, i, ln);
                                setTabAt(nextTab, i + n, hn);
                                setTabAt(tab, i, fwd);
                                advance = true;
                            }
                        }
                    }
                }
            }
        }
        
             
    // 通过CounterCell[]数组 分而治之，在多线程下高效率的并发的增加元素的个数
     private final void fullAddCount(long x, boolean wasUncontended) {
            int h;
            if ((h = ThreadLocalRandom.getProbe()) == 0) {
                ThreadLocalRandom.localInit();      // force initialization
                h = ThreadLocalRandom.getProbe();
                wasUncontended = true;
            }
            boolean collide = false;                // True if last slot nonempty
            // 通过自旋的方式初始化CounterCells数据 并设置元素属性
            for (;;) {
                CounterCell[] as; CounterCell a; int n; long v;
                // counterCells当数组构建好后，并且CounterCell元素不为空时 封装CounterCell元素，设置值后装入CounterCells数组
                if ((as = counterCells) != null && (n = as.length) > 0) {
                    if ((a = as[(n - 1) & h]) == null) {
                        if (cellsBusy == 0) {            // Try to attach new Cell
                            CounterCell r = new CounterCell(x); // Optimistic create
                            if (cellsBusy == 0 &&
                                U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                                boolean created = false;
                                try {               // Recheck under lock
                                    CounterCell[] rs; int m, j;
                                    if ((rs = counterCells) != null &&
                                        (m = rs.length) > 0 &&
                                        rs[j = (m - 1) & h] == null) {
                                        rs[j] = r;
                                        created = true;
                                    }
                                } finally {
                                    cellsBusy = 0;
                                }
                                if (created)
                                    break;
                                continue;           // Slot is now non-empty
                            }
                        }
                        collide = false;
                    }
                    else if (!wasUncontended)       // CAS already known to fail
                        wasUncontended = true;      // Continue after rehash
                        // 设置更新指定CounterCell的value值
                    else if (U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))
                        break;
                    else if (counterCells != as || n >= NCPU)
                        collide = false;            // At max size or stale
                    else if (!collide)
                        collide = true;
                    else if (cellsBusy == 0 &&
                             U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                        try {
                            if (counterCells == as) {// Expand table unless stale
                                CounterCell[] rs = new CounterCell[n << 1];
                                for (int i = 0; i < n; ++i)
                                    rs[i] = as[i];
                                counterCells = rs;
                            }
                        } finally {
                            cellsBusy = 0;
                        }
                        collide = false;
                        continue;                   // Retry with expanded table
                    }
                    h = ThreadLocalRandom.advanceProbe(h);
                }
                // 初始化counterCells数组
                // 仅当counterCells数组为空时，并且通过CAS CELLSBUSY 标志位的方式确保只有一个线程初始化 counterCells数组
                else if (cellsBusy == 0 && counterCells == as && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                    boolean init = false;
                    try {               
                        // Initialize table
                        if (counterCells == as) {
                            // 构建长度为2的counterCells数组 
                            CounterCell[] rs = new CounterCell[2];
                            // 构建一个 CounterCell 对象 
                            // 将它设置counterCells数组的第一个元素
                            // 并且value值为1
                            rs[h & 1] = new CounterCell(x);
                            counterCells = rs;
                            init = true;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    if (init)
                        break;
                }
                else if (U.compareAndSwapLong(this, BASECOUNT, v = baseCount, v + x))
                    break;                          // Fall back on using base
            }
        }
        
}
```