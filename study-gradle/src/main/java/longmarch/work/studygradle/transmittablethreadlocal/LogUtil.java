package longmarch.work.studygradle.transmittablethreadlocal;

import org.slf4j.Logger;

/**
 * Description:
 * date: 2023/6/21 09:15
 *
 * @author longjiaocao
 */
public class LogUtil {

    private Logger log;

    private String traceId;

//    private static TransmittableThreadLocal<LogUtil> logUtilThreadLocal = new TransmittableThreadLocal<>();
    private static InheritableThreadLocal<LogUtil> logUtilThreadLocal = new InheritableThreadLocal<>();

//        private static ThreadLocal<LogUtil> logUtilThreadLocal = new ThreadLocal<>();

    private LogUtil() {
    }

    private LogUtil(Logger log, String name) {
        this.log = log;
        this.traceId = name + " " + System.currentTimeMillis();
    }

    public static LogUtil create(Logger log, String name) {
        LogUtil logUtil = new LogUtil(log, name);
        logUtilThreadLocal.set(logUtil);
        return logUtil;
    }

    public void info(String info) {
        log.info(traceId + " " + info);
    }

    public void error(String error) {
        log.error(traceId + " " + error);
    }

    public void info(String info, Object... values) {
        log.info(traceId + " " + info, values);
    }

    public void error(String error, Object... values) {
        log.error(traceId + " " + error, values);
    }

    public static LogUtil getLogUtil() {
        return logUtilThreadLocal.get();
    }

    public static void removeLogUtil() {
        logUtilThreadLocal.remove();
    }


}