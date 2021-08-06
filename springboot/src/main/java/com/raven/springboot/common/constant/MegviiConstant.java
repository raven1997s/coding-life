package com.raven.springboot.common.constant;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @PackageName: com.raven.springboot.common.constant
 * @ClassName: MegviiConstant
 * @Blame: raven
 * @Date: 2021-08-03 10:20
 * @Description:
 */
public class MegviiConstant {

    public static final String GET_TOKEN_URL = "https://api.megvii.com/faceid/lite/get_token";
    public static final String GET_RESULT__URL = "https://api.megvii.com/faceid/lite/get_result";
    public static final String ERROR_MESSAGE = "error_message";

    /**
     * 表示目前 FaceID Lite 的使用状态
     */
    public static final String OK = "OK";
    private static final String ERROR_STATUS_NOT_STARTED = "NOT_STARTED";
    private static final String ERROR_STATUS_PROCESSING = "PROCESSING";
    private static final String ERROR_STATUS_WEBRTC_UNSUPPORTED = "WEBRTC_UNSUPPORTED";
    private static final String ERROR_STATUS_FAILED = "FAILED";
    private static final String ERROR_STATUS_CANCELLED = "CANCELLED";
    private static final String ERROR_STATUS_TIMEOUT = "TIMEOUT";

    /**
     * 人脸比对结果
     */
    private static final String VERIFY_ERROR_MESSAGE_NO_SUCH_ID_NUMBER = "NO_SUCH_ID_NUMBER";
    private static final String VERIFY_ERROR_MESSAGE_ID_NUMBER_NAME_NOT_MATCH = "ID_NUMBER_NAME_NOT_MATCH";
    private static final String VERIFY_ERROR_MESSAGE_IMAGE_ERROR_UNSUPPORTED_FORMAT = "IMAGE_ERROR_UNSUPPORTED_FORMAT";
    private static final String VERIFY_ERROR_MESSAGE_NO_FACE_FOUND = "NO_FACE_FOUND";
    private static final String VERIFY_ERROR_MESSAGE_DATA_SOURCE_ERROR = "DATA_SOURCE_ERROR";
    private static final String VERIFY_ERROR_MESSAGE_INTERNAL_ERROR = "INTERNAL_ERROR";

    /**
     * 活体检测结果
     */
    public static final String LIVENESS_RESULT_PASS = "PASS";

    private static Map<String, String> ERROR_STATUS_MAP = Maps.newHashMap();
    private static Map<String, String> VERIFY_ERROR_MESSAGE__MAP = Maps.newHashMap();

    static {
        ERROR_STATUS_MAP.put(ERROR_STATUS_NOT_STARTED, "还没有开始验证");
        ERROR_STATUS_MAP.put(ERROR_STATUS_PROCESSING, "正在进行 FaceID Lite 验证");
        ERROR_STATUS_MAP.put(ERROR_STATUS_WEBRTC_UNSUPPORTED, "表示浏览器不支持引起失败");
        ERROR_STATUS_MAP.put(ERROR_STATUS_FAILED, "验证流程未完成或出现异常");
        ERROR_STATUS_MAP.put(ERROR_STATUS_CANCELLED, "用户主动取消了验证流程");
        ERROR_STATUS_MAP.put(ERROR_STATUS_TIMEOUT, "流程超时");

        VERIFY_ERROR_MESSAGE__MAP.put(VERIFY_ERROR_MESSAGE_NO_SUCH_ID_NUMBER, "没有此身份证号码的记录");
        VERIFY_ERROR_MESSAGE__MAP.put(VERIFY_ERROR_MESSAGE_ID_NUMBER_NAME_NOT_MATCH, "身份证号码与提供的姓名不匹配");
        VERIFY_ERROR_MESSAGE__MAP.put(VERIFY_ERROR_MESSAGE_IMAGE_ERROR_UNSUPPORTED_FORMAT, "姓名和身份证号正确，但图片无法解析或者没有可比对图片");
        VERIFY_ERROR_MESSAGE__MAP.put(VERIFY_ERROR_MESSAGE_NO_FACE_FOUND, "对应的图像没有检测到人脸");
        VERIFY_ERROR_MESSAGE__MAP.put(VERIFY_ERROR_MESSAGE_DATA_SOURCE_ERROR, "调用比对数据发生错误");
        VERIFY_ERROR_MESSAGE__MAP.put(VERIFY_ERROR_MESSAGE_INTERNAL_ERROR, "服务器内部错误,请及时联系运营人员");
    }

    /**
     * 获取FaceID Lite 的使用状态
     * @param status
     * @return
     */
    public static String getStatusDesc(String status) {
        return StringUtils.isNotEmpty(ERROR_STATUS_MAP.get(status)) ? ERROR_STATUS_MAP.get(status) : OK;
    }

    /**
     * 获取人脸比对结果错误信息
     * @param errorMessage
     * @return
     */
    public static String getVerifyResultErrorMessage(String errorMessage) {
        return StringUtils.isNotEmpty(VERIFY_ERROR_MESSAGE__MAP.get(errorMessage)) ? VERIFY_ERROR_MESSAGE__MAP.get(errorMessage) : OK;
    }
}


