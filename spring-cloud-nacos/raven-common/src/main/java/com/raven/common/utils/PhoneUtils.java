package com.raven.common.utils;

import org.apache.commons.lang3.StringUtils;

public class PhoneUtils {

    private PhoneUtils() {
    }

    public static final void checkPhone(String phone) {
        if (StringUtils.isNotBlank(phone) && phone.startsWith("1") && phone.length() == 11) {
            return;
        }
        throw new RuntimeException("手机号格式不对");
    }
}
