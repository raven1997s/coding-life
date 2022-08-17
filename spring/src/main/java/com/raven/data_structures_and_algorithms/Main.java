package com.raven.data_structures_and_algorithms;

import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;

/**
 * Description:
 * date: 2022/8/15 10:31
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(encrypt3ToMD5(l + ""));
    }

    /**
     * MD5加密之方法三
     * @explain springboot自带MD5加密
     * @param str
     *            待加密字符串
     * @return 16进制加密字符串
     */
    public static String encrypt3ToMD5(String str) {
        String md5 = "  ";
        try {
            md5 = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }

}