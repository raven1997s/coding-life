package com.raven.pattern.adapter.poweradapter;

/**
 * @PackageName: com.raven.pattern.adapter.poweradapter
 * @ClassName: Dc5
 * @Blame: raven
 * @Date: 2021-08-04 9:52
 * @Description: 直流电 5V转换器接口
 */
public interface Dc5 {

    /**
     * 输出5V电流
     * @return
     */
    int output5V();
}
