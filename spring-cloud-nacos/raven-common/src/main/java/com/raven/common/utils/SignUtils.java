package com.raven.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author qin
 * @Description:
 * @date 2020-06-01
 */
@Slf4j
public class SignUtils {

	public static String getSign(Map<String, String> params, String secretKey) {

		StringBuilder sb = new StringBuilder();
		// 将参数以参数名的字典升序排序
		Map<String, String> sortParams = new TreeMap<String, String>(params);
		// 遍历排序的字典,并拼接"key=value"格式
		for (Map.Entry<String, String> entry : sortParams.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().trim();
			if (StringUtils.equalsIgnoreCase("sign",key)) {
				continue;
			}
			if (!StringUtils.isEmpty(value)) {
				sb.append("&").append(key).append("=").append(value);
			}
		}
		String signStr = sb.toString().replaceFirst("&", "");
		String stringSignTemp = signStr + "&" + "secretKey=" + secretKey;
		//将签名使用MD5加密并全部字母变为大写
		String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
		if (log.isDebugEnabled()) {
			log.debug("sign str = {} and sign value = {}", stringSignTemp, signValue);
		}
		return signValue.substring(0, 30);
	}

}
