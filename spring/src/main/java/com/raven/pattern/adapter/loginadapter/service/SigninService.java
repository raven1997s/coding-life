package com.raven.pattern.adapter.loginadapter.service;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.v1.service
 * @ClassName: SiginService
 * @Blame: raven
 * @Date: 2021-08-04 10:08
 * @Description: 模拟登录注册接口
 */
public class SigninService {

    public MsgResult regist(String username,String password){
        return MsgResult.success();
    }


    public MsgResult login(String username,String password){
        return MsgResult.success();
    }
}
