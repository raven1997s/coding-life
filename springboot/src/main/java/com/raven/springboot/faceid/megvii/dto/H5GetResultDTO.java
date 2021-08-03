package com.raven.springboot.faceid.megvii.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @PackageName: com.raven.springboot.faceid.megvii.dto
 * @ClassName: H5GetResultDTO
 * @Blame: raven
 * @Date: 2021-08-03 11:27
 * @Description:
 */
@Data
@Builder
public class H5GetResultDTO {
    @ApiModelProperty(value = "调用此API的api_key", required = true)
    private String api_key;

    @ApiModelProperty(value = "调用此API的api_key的secret", required = true)
    private String api_secret;

    @ApiModelProperty(value = "通过 get_token, notify_url 或者 return_url 返回的活体业务编号。",required = true)
    private String biz_id;

    @ApiModelProperty(value = "是否返回活体图像数据：\n" +
            "0（默认）：不需要图像\n" +
            "1：需要返回最佳活体质量图(image_best，当procedure_type为\"video\"，\"still\"或\"flash\"时有效）\n" +
            "2：需要返回身份证人像面图像\n" +
            "3：需要返回身份证国徽面图像\n" +
            "4：需要返回所有图像\n" +
            "5：需要返回正脸自拍照片（仅当procedure_type为selfie时有效）\n" +
            "6：需要返回侧脸自拍照片（仅当procedure_type为selfie时有效）", required = true)
    private String return_image;
}
