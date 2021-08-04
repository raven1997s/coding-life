package com.raven.pattern.adapter.loginadapter.service.v2;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;
import com.raven.pattern.adapter.loginadapter.service.SigninService;
import com.raven.pattern.adapter.loginadapter.service.v2.adapter.LoginAdapter;
import com.raven.pattern.adapter.loginadapter.service.v2.adapter.LoginForPhoneAdapter;
import com.raven.pattern.adapter.loginadapter.service.v2.adapter.LoginForQQAdapter;
import com.raven.pattern.adapter.loginadapter.service.v2.adapter.LoginForWechatAdapter;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.service.v2
 * @ClassName: PassportForThirdAdapter
 * @Blame: raven
 * @Date: 2021-08-04 10:51
 * @Description: 登录认证适配器
 * 继承原有的登录方式，实现新定义的登录方式
 */
public class PassportForThirdAdapter extends SigninService implements IPassportForThird {


    /**
     * 校验适配器，将真正的实现交给QQAdapter实现
     *
     * @param openId
     * @return
     */
    @Override
    public MsgResult loginForQQ(String openId) {
//        LoginAdapter adapter = new LoginForQQAdapter();
//        if (adapter.support(adapter)) {
//            return adapter.login(openId, adapter);
//        }
        return processLogin(openId, LoginForQQAdapter.class);
    }


    /**
     * 校验适配器，将真正的实现交给WechatAdapter实现
     *
     * @param openId
     * @return
     */
    @Override
    public MsgResult loginForWechat(String openId) {
//        LoginAdapter adapter = new LoginForWechatAdapter();
//        if (adapter.support(adapter)) {
//            return adapter.login(openId, adapter);
//        }
        return processLogin(openId, LoginForWechatAdapter.class);
    }

    /**
     * 校验适配器，将真正的实现交给PhoneAdapter实现
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public MsgResult loginForPhone(String phone, String code) {
//        LoginAdapter adapter = new LoginForPhoneAdapter();
//        if (adapter.support(adapter)) {
//            return adapter.login(phone, adapter);
//        }
        return processLogin(phone, LoginForPhoneAdapter.class);
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

    /**
     * 方法抽象
     *
     * @param key
     * @param clazz
     * @return
     */
    private MsgResult processLogin(String key, Class<? extends LoginAdapter> clazz) {
        try {
            LoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter)) {
                return adapter.login(key, adapter);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
