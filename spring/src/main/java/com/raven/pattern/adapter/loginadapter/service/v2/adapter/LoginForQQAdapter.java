package com.raven.pattern.adapter.loginadapter.service.v2.adapter;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.service.v2.adapter
 * @ClassName: LoginForQQAdapter
 * @Blame: raven
 * @Date: 2021-08-04 10:56
 * @Description:
 */
public class LoginForQQAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    /**
     * 定义通过QQ号登录的逻辑
     * @param id
     * @param adapter
     * @return
     */
    @Override
    public MsgResult login(String id, Object adapter) {
        return null;
    }
}
