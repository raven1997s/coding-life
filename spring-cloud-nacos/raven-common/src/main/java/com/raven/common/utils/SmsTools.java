package com.raven.common.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
public class SmsTools {
	private static final Logger errorLogger = LoggerFactory.getLogger("errorLogger");
	private final static String url = "http://sdk2.entinfo.cn:8060/webservice.asmx/SendSMS";
	private final static Integer TIMEOUT = 5000;

	public static void send(String sense, String phone, String msg) {
		StringBuilder sb = new StringBuilder("发送短信|");
		sb.append(sense).append("|");
		sb.append(phone).append("|");
		sb.append(msg).append("|");
		String response;
		HttpURLConnection con = null;
		try {
			Map<String, String> parameters = Maps.newLinkedHashMap();
			parameters.put("sn", "SDK-WKS-010-00640");
			parameters.put("pwd", "855712");
			parameters.put("mobile", phone);
			parameters.put("content", msg + "【e地跑】");
			con = (HttpURLConnection) new URL(url + "?" + ParameterStringBuilder.getParamsString(parameters)).openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(TIMEOUT);
			con.setReadTimeout(TIMEOUT);
			response = con.getResponseMessage();
		} catch (Exception e) {
			response = "Error:" + e.getMessage();
			errorLogger.error("SmsTools.send error", e);
		}finally{
			if(con!=null){
				con.disconnect();
			}
		}
		sb.append(response).append("|");
		log.info(sb.toString());
	}

	private static class ParameterStringBuilder {
		public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
			StringBuilder result = new StringBuilder();
			String enc = "GBK";

			for (Map.Entry<String, String> entry : params.entrySet()) {
				result.append(URLEncoder.encode(entry.getKey(), enc));
				result.append("=");
				result.append(URLEncoder.encode(entry.getValue(), enc));
				result.append("&");
			}

			String resultString = result.toString();
			return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
		}
	}
}