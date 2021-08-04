package com.raven.pattern.adapter.loginadapter.service.v1;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;
import com.raven.pattern.adapter.loginadapter.service.SigninService;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.v1.service
 * @ClassName: SigninForThridService
 * @Blame: raven
 * @Date: 2021-08-04 10:23
 * @Description: 系统升级，新增其他登录注册方式
 * 传统方式：通过继承之前的登录注册服务，提供更多的服务
 */
public class SigninForThirdService extends SigninService {

    /**
     * 新的注册登录方式，通过QQ登录
     *
     * @param openId
     * @return
     */
    public MsgResult loginForQQ(String openId) {
        // 1.openId是全局唯一，可以将它当成一个用户名(加长)
        // 2.密码默认为QQ_EMPTY
        // 3.注册(在原有系统里面创建一个新的用户)
        // 4.调用原来的登录方式
        return loginForRegist(openId, null);
    }

    public MsgResult loginForWechat(String openId) {
        return null;
    }

    public MsgResult loginForPhone(String phone, String code) {
        return null;
    }

    /**
     * 调用父类的注册登录方法，完成注册登录
     *
     * @param username
     * @param password
     * @return
     */
    private MsgResult loginForRegist(String username, String password) {
        super.regist(username, password);
        return super.login(username, password);
    }
}
