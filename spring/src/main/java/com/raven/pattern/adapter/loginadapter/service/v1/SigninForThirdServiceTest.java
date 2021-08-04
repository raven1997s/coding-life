package com.raven.pattern.adapter.loginadapter.service.v1;

/**
 * @PackageName: com.raven.pattern.adapter.loginadapter.v1.service
 * @ClassName: SigninForThirdServiceTest
 * @Blame: raven
 * @Date: 2021-08-04 10:33
 * @Description:
 */
public class SigninForThirdServiceTest {

    public static void main(String[] args) {
        SigninForThirdService signinForThirdService = new SigninForThirdService();
        // 原来的登录方式
        signinForThirdService.login("username", "password");

        // 通过继承实现新增其他的登录方式
        signinForThirdService.loginForPhone("18998986655", "8686");
        signinForThirdService.loginForQQ("asdasdwqeqw");
        signinForThirdService.loginForWechat("ssadasdasd");
    }
}
