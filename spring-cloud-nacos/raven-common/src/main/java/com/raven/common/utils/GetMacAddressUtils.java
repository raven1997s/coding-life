package com.raven.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * Description: 
 * date: 2021/3/17 17:53
 * @author YeJiang
 * @version
 */
public class GetMacAddressUtils {
    public static String getMACAddress() {
        try {
            // 获得ip
            NetworkInterface netInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            // 获得Mac地址的byte数组
            String macAddress = new String(netInterface.getHardwareAddress());
            return macAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
