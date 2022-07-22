package com.raven.stream.function;

/**
 * Description:
 * date: 2022/7/22 13:55
 *
 * @author raven
 */
public class VUtils {

    /**
     * 如果参数为true 抛出异常
     *
     * @param b
     * @return
     */
    public static ThrowExceptionFunction isTrue(boolean b) {
        return message -> {
            if (b) {
                throw new RuntimeException(message);
            }
        };
    }

    /**
     * 参数为true 或者是 false的时候执行不同的逻辑
     * @param b
     * @return
     */
    public static BranchHandle isTrueOrFalse(boolean b) {
        return (trueHandler, falseHandler) -> {
            if (b) {
                trueHandler.run();
            } else {
                falseHandler.run();
            }
        };
    }

}