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
        return pareseGetResultResponseJson(responseJson);
    }

    private MsgResult pareseGetResultResponseJson(String responseJson) {
        JSONObject jsonObject = JSONObject.parseObject(responseJson);
        String errorMessage = jsonObject.getString(MegviiConstant.ERROR_MESSAGE);
        if (StringUtils.isNotBlank(errorMessage)){
            log.info("Megvii | 获取result请求失败，失败原因 {}",errorMessage);
            return MsgResult.error("获取result请求失败");
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
}
