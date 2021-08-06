package com.raven.springboot.faceid.megvii.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.raven.springboot.common.constant.MegviiConstant;
import com.raven.springboot.common.constant.MsgResult;
import com.raven.springboot.common.properties.MegviiProperties;
import com.raven.springboot.common.utils.HttpClientUtil;
import com.raven.springboot.faceid.megvii.dto.H5GetResultDTO;
import com.raven.springboot.faceid.megvii.dto.H5GetTokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @PackageName: com.raven.springboot.service.faceid.megvii
 * @ClassName: FaceRecognitionService
 * @Blame: raven
 * @Date: 2021-08-02 15:35
 * @Description:
 */
@Service
@Slf4j
public class FaceRecognitionService {

    @Autowired
    private MegviiProperties megviiProperties;

    public MsgResult getToken(String bizNo, String idcardNumber, String idcardName) {

        // 构建请求参数
        H5GetTokenDTO getTokenDTO = builderH5GetTokenDTO(bizNo, idcardNumber, idcardName);

        // 调用获取tokenUrl
        String responseJson =  requestMegviiGetTokenUrl(getTokenDTO);

        // 返回调用结果
        return pareseGetTokenResponseJson(responseJson);
    }

    public MsgResult getResult(String bizNo){
        // 构建请求参数
        H5GetResultDTO getResultDTO = builderH5GetResultDTO(bizNo);

        // 调用获取tokenUrl
        String responseJson =  requestMegviiGetResultUrl(getResultDTO);

        // 返回调用结果
        return paresGetResultResponseJson(responseJson);
    }

    private MsgResult paresGetResultResponseJson(String responseJson) {
        JSONObject jsonObject = JSONObject.parseObject(responseJson);
        String errorMessage = jsonObject.getString(MegviiConstant.ERROR_MESSAGE);
        if (StringUtils.isNotBlank(errorMessage)){
            log.info("Megvii | 获取result请求失败，失败原因 {}",errorMessage);
            return MsgResult.error("获取result请求失败");
        }

        MsgResult msgResult = this.checkReturnDataInfo(JSON.toJSONString(jsonObject));
        if (!msgResult.hasSuccess()){
            return msgResult;
        }

        return MsgResult.success(jsonObject);
    }

    private String requestMegviiGetResultUrl(H5GetResultDTO getResultDTO) {
        Map<String,String> map = JSON.parseObject(JSON.toJSONString(getResultDTO), Map.class);
        return HttpClientUtil.doGet(MegviiConstant.GET_RESULT__URL, map);
    }

    private H5GetResultDTO builderH5GetResultDTO(String bizNo) {
        return H5GetResultDTO.builder()
                .api_key(megviiProperties.getAppKey())
                .api_secret(megviiProperties.getSecret())
                .biz_id(bizNo)
                .return_image("4")
                .build();
    }

    private MsgResult pareseGetTokenResponseJson(String responseJson) {
        JSONObject jsonObject = JSONObject.parseObject(responseJson);
        String errorMessage = jsonObject.getString(MegviiConstant.ERROR_MESSAGE);
        if (StringUtils.isNotBlank(errorMessage)){
            log.info("Megvii | 获取token请求失败，失败原因 {}",errorMessage);
            return MsgResult.error("获取token请求失败");
        }
        return MsgResult.success(jsonObject);
    }

    private String requestMegviiGetTokenUrl(H5GetTokenDTO getTokenDTO) {
        Map<String,String> map = JSON.parseObject(JSON.toJSONString(getTokenDTO), Map.class);
        return HttpClientUtil.doPost(MegviiConstant.GET_TOKEN_URL, map);
    }

    private H5GetTokenDTO builderH5GetTokenDTO(String bizNo, String idcardNumber, String idcardName) {
        return H5GetTokenDTO.builder()
                .api_key(megviiProperties.getAppKey())
                .api_secret(megviiProperties.getSecret())
                .return_url(megviiProperties.getReturnUrl())
                .notify_url(megviiProperties.getNotifyUrl())
                .biz_no(bizNo)
                .web_title(megviiProperties.getWebTitle())
                // “1”表示KYC验证，表示最终的用户自拍照片将于参考照片比对
                .comparison_type("1")
                // 0：不拍摄身份证，而是通过 idcardName / idcardNumber 参数传入；
                .idcard_mode("0")
                .idcard_number(idcardNumber)
                .idcard_name(idcardName)
                .build();
    }
    /**
     * 校验人脸识别信息
     *
     * @param dataJson
     * @return
     */
    public MsgResult checkReturnDataInfo(String dataJson) {

        if (StringUtils.isEmpty(dataJson)) {
            log.error("Megvii | 旷视人脸识别消息接收错误,dataJson = {}", dataJson);
            return MsgResult.error("人脸识别错误！");
        }

        JSONObject dataJsonObject = JSONObject.parseObject(dataJson);

        // 检验FaceID Lite 的使用状态
        String requestId = dataJsonObject.getString("request_id");
        String status = dataJsonObject.getString("status");
        String statusDesc = MegviiConstant.getStatusDesc(status);
        if (!StringUtils.equals(MegviiConstant.OK, statusDesc)) {
            log.error("Megvii | 旷视人脸识别消息接收错误, request_id:{}, 错误原因:{}", requestId, statusDesc);
            return MsgResult.error("人脸识别错误！");
        }

        // 活体检测结果
        JSONObject livenessResult = dataJsonObject.getJSONObject("liveness_result");
        String result = livenessResult.getString("result");
        if (!StringUtils.equals(MegviiConstant.LIVENESS_RESULT_PASS, result)) {
            log.error("Megvii | 旷视人脸识别消息接收错误, 活体检测失败, request_id:{}", requestId);
            return MsgResult.error("人脸识别错误！");
        }


        // 人脸比对结果风险
        JSONObject verifyResult = dataJsonObject.getJSONObject("verify_result");
        if (this.verifyResultRisk(verifyResult, requestId)) {
            return MsgResult.error("人脸识别错误！");
        }

        return MsgResult.success();
    }

    /**
     * 校验是否为同一人
     *
     * @param verifyResult
     * @param requestId
     * @return
     */
    private boolean verifyResultRisk(JSONObject verifyResult, String requestId) {
        if (Objects.isNull(verifyResult)) {
            return true;
        }

        String verifyResultErrorMessage = MegviiConstant.getVerifyResultErrorMessage(MegviiConstant.ERROR_MESSAGE);
        if (!StringUtils.equals(MegviiConstant.OK, verifyResultErrorMessage)) {
            log.error("Megvii | 旷视人脸识别消息接收错误, 在做人脸比对的时候出现错误, request_id:{}, 错误原因:{}", requestId, verifyResult.getString(MegviiConstant.ERROR_MESSAGE));
            return true;
        }

        JSONObject resultFaceid = verifyResult.getJSONObject("result_faceid");
        // 综合分数的置信度
        Double confidence = resultFaceid.getDouble("confidence");
        JSONObject thresholds = resultFaceid.getJSONObject("thresholds");
        // 风险为万分之一的置信度阈值
        Double score = thresholds.getDouble("1e-4");
        if (confidence < score) {
            log.error("Megvii | 旷视人脸识别消息接收错误, 系统认证不是同一个人, request_id:{}", requestId);
            return true;
        }
        return false;
    }

}
