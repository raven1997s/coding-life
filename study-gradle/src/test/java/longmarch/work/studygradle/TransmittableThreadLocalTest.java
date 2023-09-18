package longmarch.work.studygradle;

import lombok.extern.slf4j.Slf4j;
import longmarch.work.studygradle.transmittablethreadlocal.LogUtil;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2023/9/14 15:08
 *
 * @author longjiaocao
 */
@Slf4j
public class TransmittableThreadLocalTest extends StudyGradleApplicationTests{


    private static final Integer BIG_FILE_PAGE_SIZE = 10000;
    /**
     * 计算可用的处理器核心数
     */
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = Math.max(AVAILABLE_PROCESSORS, 2);
    private static final int MAXIMUM_POOL_SIZE = AVAILABLE_PROCESSORS * 2;

    // 创建自定义线程池
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,                 // 核心线程数
            MAXIMUM_POOL_SIZE,              // 最大线程数
            60,                           // 线程空闲超时时间（单位：秒）
            TimeUnit.SECONDS,             // 时间单位
            new LinkedBlockingQueue<>(),  // 任务队列（无界队列）
            Executors.defaultThreadFactory(),  // 线程工厂
            new ThreadPoolExecutor.CallerRunsPolicy()  // 拒绝策略（使用调用线程执行被拒绝的任务）
    );

    @Test
    public void test2(){
        LogUtil logUtil = LogUtil.create(log, "[test2] | ");
        test3();
        logUtil.info("ExportHelper - 开始导出Excel文件{}", "start");
        for (int i = 0; i < 10; i++) {
            THREAD_POOL.execute(() -> {
                logUtil.info("ExportHelper - Excel文件 {} 导出中...", "aaa");
            });
        }
        logUtil.info("ExportHelper - 开始导出Excel文件{}", "end");
    }

    private void test3() {
        LogUtil logUtil = LogUtil.getLogUtil();
        logUtil.info("ExportHelper - 开始导出Excel文件{}", "mid");
    }

    @Test
    public void test(){
        LogUtil logUtil = LogUtil.create(log, "[TransmittableThreadLocalTest] | ");

        logUtil.info(Thread.currentThread().getName() +   " : output sth msg");

        doSth();
//        doSth2();

        logUtil.info(Thread.currentThread().getName() +   " : output sth msg");

    }

    @SuppressWarnings("all")
    private void doSth2() {
        LogUtil logUtil = LogUtil.getLogUtil();
        String a = null;
        logUtil.info("ExportHelper - 表头字段:{}", a);
    }

    private static void doSth() {
        new Thread(()->{
            LogUtil logUtil = LogUtil.getLogUtil();
            logUtil.info(Thread.currentThread().getName() +   " : output sth msg");
        }).start();
    }
}