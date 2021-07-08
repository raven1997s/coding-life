package com.raven.common.utils;

import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>Description: 导出工具 </p>
 * @author: by qulibin
 * @date: 2018/9/11  下午5:03
 * @version: 1.0
 */
public class ExportUtils {

	public static void exportFile(String fileName, HttpServletResponse response, byte[] in) throws Exception {
		// 解决不同浏览器出现的乱码
		fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
		FileCopyUtils.copy(in, response.getOutputStream());
	}
}
