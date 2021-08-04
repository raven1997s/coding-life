package com.raven.pattern.adapter.loginadapter.service.v2;

import com.raven.pattern.adapter.loginadapter.constant.MsgResult;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.v2
 * @ClassName: IPassportForThird
 * @Blame: raven
 * @Date: 2021-08-04 10:44
 * @Description: 定义登录接口规范
 */
public interface IPassportForThird {

    MsgResult loginForQQ(String openId);

    MsgResult loginForWechat(String openId);

    MsgResult loginForPhone(String phone,String code);

}
