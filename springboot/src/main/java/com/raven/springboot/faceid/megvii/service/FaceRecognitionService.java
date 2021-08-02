package com.raven.springboot.faceid.megvii.service;

import com.raven.springboot.common.MsgResult;
import com.raven.springboot.faceid.megvii.dto.H5GetTokenDTO;
import org.springframework.beans.factory.annotation.Value;

/**
 * @PackageName: com.raven.springboot.service.faceid.megvii
 * @ClassName: FaceRecognitionService
 * @Blame: raven
 * @Date: 2021-08-02 15:35
 * @Description:
 */
public class FaceRecognitionService {

    @Value("${megvii.secret}")
    private String secret;

    @Value("${megvii.appKey}")
    private String appKey;

    @Value("${megvii.returnUrl}")
    private String returnUrl;

    @Value("${megvii.notifyUrl}")
    private String notifyUrl;

    public MsgResult getToken(String bizNo){


        H5GetTokenDTO getTokenDTO = builderH5GetTokenDTO( bizNo);


        return MsgResult.success();
    }

    private H5GetTokenDTO builderH5GetTokenDTO(String bizNo) {
        return H5GetTokenDTO.builder()
                .apiKey(appKey)
                .apiSecret(secret)
                .returnUrl(returnUrl)
                .notifyUrl(notifyUrl)
                .bizNo(bizNo)
                .build();
    }
}
