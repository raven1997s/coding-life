package com.raven.pattern.adapter.loginadapter.service.v2.adapter;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.service.v2.adapter
 * @ClassName: LoginAdapter
 * @Blame: raven
 * @Date: 2021-08-04 10:52
 * @Description: 在适配器里面 这个接口可有可无，不要和模板模式混淆
 * 模板模式一定是抽象类，而这里仅仅只是一个接口
 */
public interface LoginAdapter {

    /**
     * 是否支持该适配器
     * @param adapter
     * @return
     */
    boolean support(Object adapter);

    /**
     * 登录
     * @param id
     * @param adapter
     * @return
     */
    MsgResult login(String id ,Object adapter);
}
