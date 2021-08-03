package com.raven.springboot.faceid.megvii.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @PackageName: com.raven.springboot.faceid.megvii.dto
 * @ClassName: GetTokenDTO
 * @Blame: raven
 * @Date: 2021-08-02 15:55
 * @Description: 移动端KYC验证服务 - KYC验证模式
 */
@Data
@Builder
public class H5GetTokenDTO {

    @ApiModelProperty("调用此API的api_key")
    private String api_key;

    @ApiModelProperty("调用此API的api_key的secret")
    private String api_secret;

    @ApiModelProperty("用户完成或取消验证后网页跳转的目标URL")
    private String return_url;

    @ApiModelProperty("用户完成验证、取消验证、或验证超时后，由FaceID服务器请求客户服务器的URL。")
    private String notify_url;

    @ApiModelProperty("客户业务流水号，该号必须唯一。并会在notify和return时原封不动的返回给您的服务器，以帮助您确认每一笔业务的归属。")
    private String biz_no;

    @ApiModelProperty("验证网页展示用的标题文字,此参数默认为空，此时系统将采用默认的标题。")
    private String web_title;

    @ApiModelProperty("设定本次服务的类型，目前支持的比对类型为“KYC验证”（取值“1”）或“人脸比对”（取值“0”）。传递其他值调用将识别，返回错误码400（BAD_ARGUMENTS）。\n" +
            "“1”表示KYC验证，表示最终的用户自拍照片将于参考照片比对。\n" +
            "“0”表示人脸比对，FaceID将使用用户自己提供的照片（参数image_ref[x]）作为比对人脸照。\n" +
            "请注意：\n" +
            "本参数影响验证流程中是否存在身份证拍摄环节：如果为“1”，则可选择包含身份证拍摄；如果为“0”，验证流程中将没有身份证拍摄。\n" +
            "本参数取什么值将决定下面“二选一”参数组使用哪一组参数。")
    private String comparison_type;

    @ApiModelProperty("传递参数“0”，“1”，“2”，“3”或“4”，表示获取用户身份证信息的方法。传递其他值调用将识别，返回错误码400（BAD_ARGUMENTS）。\n" +
            "0：不拍摄身份证，而是通过 idcard_name / idcard_number 参数传入；\n" +
            "1：仅拍摄身份证人像面，可获取人像面所有信息；\n" +
            "2：拍摄身份证人像面和身份证国徽面，可获取身份证所有信息；\n" +
            "3：不拍摄身份证，但会要求用户在界面上输入身份证号和姓名；\n" +
            "4：拍摄身份证人像面或用户输入身份证号和姓名，用户可在界面上自行选择身份证录入方法。注意：该参数只有在控制台中选择使用“浅色主题”时才生效，若未应用浅色主题而传入4，则返回错误码400（BAD_ARGUMENTS）。")
    private String idcard_mode;

    /**
     * idcard_mode = 0 时，idcard_name idcard_number 这两个参数必须传；在其他情况下可以不传，即使传递了也不会使用。
     */
    @ApiModelProperty("idcard_name， 需要KYC验证对象的姓名，使用UTF-8编码；")
    private String idcard_name;
    @ApiModelProperty("idcard_number， 需要KYC验证对象的身份证号，也就是一个18位长度的字符串。")
    private String idcard_number;
}
