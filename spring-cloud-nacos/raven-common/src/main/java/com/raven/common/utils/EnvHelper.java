package com.raven.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: qulibin
 * @description: 环境变量
 * @date: 下午1:23 2018/9/5
 * @modify：
 */
public class EnvHelper {

    private EnvHelper() {
    }

    public static final String ENV_DEV = "dev";
    public static final String ENV_TEST = "test";
    public static final String ENV_PREPRO = "stage";
    public static final String ENV_PRO = "prod";

    private static final String[] evnArray = {ENV_DEV, ENV_TEST, ENV_PREPRO, ENV_PRO};
    private static String env;

    public static boolean isDev() {
        return EnvHelper.getEnv().equals(ENV_DEV);
    }

    public static boolean isTest() {
        return EnvHelper.getEnv().equals(ENV_TEST);
    }

    public static boolean isPrePro() {
        return EnvHelper.getEnv().equals(ENV_PREPRO);
    }

    public static boolean isPro() {
        return EnvHelper.getEnv().equals(ENV_PRO);
    }

    /**
     * 获取配置的当前环境变量
     *
     * @return
     */
    public static String getEnv() {
        if (StringUtils.isNotBlank(env)) {
            return env;
        }

        if (StringUtils.isBlank(env)) {
            env = System.getProperty("spring.profiles.active");
            if (StringUtils.isBlank(env)) {
                env = System.getenv("environment");
            }
            if (StringUtils.isNotBlank(env)) {
                return env;
            }
            throw new RuntimeException("environment is null");
        }

        for (String envAllowed : evnArray) {
            if (envAllowed.equalsIgnoreCase(env)) {
                return env;
            }
        }
        throw new RuntimeException("Set the environment to one in (dev,test,pro)");
    }

    public static void setActive(String active) {
        EnvHelper.env = active;
    }
}
