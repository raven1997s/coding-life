package com.raven.pattern.adapter.loginadapter.service.v2.adapter;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.service.v2.adapter
 * @ClassName: LoginForPhoneAdapter
 * @Blame: raven
 * @Date: 2021-08-04 10:57
 * @Description:
 */
public class LoginForPhoneAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForPhoneAdapter;
    }


    /**
     * 定义通过手机号登录的逻辑
     * @param id
     * @param adapter
     * @return
     */
    @Override
    public MsgResult login(String id, Object adapter) {
        return null;
    }
}
