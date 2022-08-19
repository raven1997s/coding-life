package com.raven.selenium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2022/8/18 16:56
 *
 * @author raven
 */
@Component
public class JiNanChangJiuAutoLogin implements CommandLineRunner {
    @Autowired
    private AutoLoginService autoLoginService;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> autoLoginService.autoLogin("JiNanChangJiu", "BYZ0001106", "edp@2021")).start();
    }
}