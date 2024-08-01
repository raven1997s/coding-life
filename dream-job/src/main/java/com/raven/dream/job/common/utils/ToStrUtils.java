package com.raven.dream.job.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 对象字符串输出工具类,一般用于日志输出
 */
@Slf4j
public class ToStrUtils {

    private static ObjectMapper objectMapper;
    private static RecursiveToStringStyle style;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        style = new RecursiveToStringStyle();
    }

    public static String toStr(Object obj) {
        if (obj == null) {
            return null;
        }
        String output;
        try {
            output = objectMapper.writeValueAsString(obj);

        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Jackson toString error...");
            // 尝试反射方式输出
            try {
                output = new ReflectionToStringBuilder(obj, style).toString();
            } catch (Exception e) {
                log.error("ReflectionToStringBuilder toString error... {}", e);
                output = obj.toString();
            }
        }
        return output;
    }
}
