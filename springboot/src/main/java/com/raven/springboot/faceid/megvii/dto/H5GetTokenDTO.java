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

    @ApiModelProperty(value = "调用此API的api_key", required = true)
    private String apiKey;

    @ApiModelProperty(value = "调用此API的api_key的secret", required = true)
    private String apiSecret;

    @ApiModelProperty(value = "用户完成或取消验证后网页跳转的目标URL", required = true)
    private String returnUrl;

    @ApiModelProperty(value = "用户完成验证、取消验证、或验证超时后，由FaceID服务器请求客户服务器的URL。", required = true)
    private String notifyUrl;

    @ApiModelProperty("客户业务流水号，该号必须唯一。并会在notify和return时原封不动的返回给您的服务器，以帮助您确认每一笔业务的归属。")
    private String bizNo;

    @ApiModelProperty("在调用notify_url和return_url时会返回的额外数据，用户可以用此接口来传递一些额外信息，以帮助您调试和信息传递。")
    private String bizExtraData;

    @ApiModelProperty("验证网页展示用的标题文字,此参数默认为空，此时系统将采用默认的标题。")
    private String webTitle;

    @ApiModelProperty("在控制台配置的对应使用场景的scene_id，用以自定义验证流程中的视觉元素。如果不传此参数，则选择在控制台中设置的默认scene；如果控制台中没有设定任何scene，则采用系统默认方案；")
    private String sceneId;

    @ApiModelProperty("\t\n" +
            "刷脸活体验证流程的选择。目前仅取以下值：\n" +
            "\"video\"：通过自拍有声视频方式进行活体验证，支持用户拍摄的一段0.5s-20s的视频。\n" +
            "\"selfie\"：通过两张自拍方式进行活体验证\n" +
            "\"still\"：通过自拍一段人脸视频进行活体认证。该值只有在控制台中选择使用“浅色主题”时才生效，若未应用浅色主题而传入\"still\"，则返回错误码400（BAD_ARGUMENTS）。支持用户拍摄一段0.5s-10s的视频。\n" +
            "“flash”，通过炫彩活体方式进行活体验证。该值只有在控制台中选择使用“浅色主题”时才生效，若未应用浅色主题而传入\"flash\"，则返回错误码400（BAD_ARGUMENTS）。\n" +
            "本参数默认值为“video”。")
    private String procedureType;

    @ApiModelProperty("此参数用来设置静默活体数据的传输方式\n" +
            "0：使用先录制视频，后上传的方式\n" +
            "1：使用webrtc技术，实时传输\n" +
            "默认状态：0")
    private String updateRtc;

    @ApiModelProperty("\t\n" +
            "本参数可选，通过本参数设置来配置不支持炫彩活体时的降级方式，目前可用参数有：\n" +
            "video：降级为自拍有声视频方式；\n" +
            "still：降级为自拍人脸视频方式；\n" +
            "keep：不进行降级，若遇到浏览器无法支持的情况，直接返回。\n" +
            "本参数默认值是‘video’，设置其他值，均会返回错误码400（BAD_ARGUMENTS）。")
    private String procedurePriority;

    @ApiModelProperty("本参数可选，通过一系列配置项来调节活体检测的严格度。目前可用的选项有：\n" +
            "“none”：表示没有特别的选项，此为默认值。\n" +
            "\"selfie_no_metadata_check\"：（仅procedure_type == “selfie”时有效）表示不限定照片格式为JPG、也不校验照片的元信息，此设置会降低安全性。\n" +
            "\"video_strict\"：（仅procedure_type == \"video\"时有效）表示针对上传的视频进行相对严格的活体检测，此设置会提高安全性，但在一定程度上影响通过率。\n" +
            "\"still_strict\"：（仅procedure_type ==\"still\"时有效）表示针对上传的静默视频进行相对严格的活体检测。\n" +
            "注意：设置其他值，均会返回400（BAD_ARGUMENTS）。")
    private String livenessPreferences;

    @ApiModelProperty("是否获取拍摄错误，本参数取值如下：\n" +
            "“1”： 获取长时间未上传录制视频错误\n" +
            "“0”：不获取长时间未上传视频错误\n" +
            "注：当本参数取值为“1”，且FaceID人脸验证过程中无法获取到用户拍摄的视频时，我们将会给return_url和notify_url中返回相应的错误信息：{\"shooting_error\":true/false}\n" +
            " 当本参数取值为“0”时，即使FaceID人脸验证过程中无法获取到用户拍摄的视频，也不返回相应的错误信息\n" +
            "本参数默认取值为“0”")
    private String getShootingError;

    @ApiModelProperty("用户点击开始拍摄后的超时时间设置，超过本时间后，用户界面将弹窗提示“长时间未收到录制视频”\n" +
            "注：本参数可选设置范围为10s-60s\n" +
            "本参数默认设置为15s，建议设置时间为10-15s")
    private int maximumShootingTime;

    @ApiModelProperty(value = "设定本次服务的类型，目前支持的比对类型为“KYC验证”（取值“1”）或“人脸比对”（取值“0”）。传递其他值调用将识别，返回错误码400（BAD_ARGUMENTS）。\n" +
            "“1”表示KYC验证，表示最终的用户自拍照片将于参考照片比对。\n" +
            "“0”表示人脸比对，FaceID将使用用户自己提供的照片（参数image_ref[x]）作为比对人脸照。\n" +
            "请注意：\n" +
            "本参数影响验证流程中是否存在身份证拍摄环节：如果为“1”，则可选择包含身份证拍摄；如果为“0”，验证流程中将没有身份证拍摄。\n" +
            "本参数取什么值将决定下面“二选一”参数组使用哪一组参数。", required = true)
    private String comparisonType;

    @ApiModelProperty(value = "传递参数“0”，“1”，“2”，“3”或“4”，表示获取用户身份证信息的方法。传递其他值调用将识别，返回错误码400（BAD_ARGUMENTS）。\n" +
            "0：不拍摄身份证，而是通过 idcard_name / idcard_number 参数传入；\n" +
            "1：仅拍摄身份证人像面，可获取人像面所有信息；\n" +
            "2：拍摄身份证人像面和身份证国徽面，可获取身份证所有信息；\n" +
            "3：不拍摄身份证，但会要求用户在界面上输入身份证号和姓名；\n" +
            "4：拍摄身份证人像面或用户输入身份证号和姓名，用户可在界面上自行选择身份证录入方法。注意：该参数只有在控制台中选择使用“浅色主题”时才生效，若未应用浅色主题而传入4，则返回错误码400（BAD_ARGUMENTS）。",required = true)
    private String idcardMode;

    @ApiModelProperty("选择用户不可编辑的字段。当选择拍摄身份证来获取上面的文字信息时，会由于OCR算法的问题导致文字识别不准确（如：生僻字、形近字），通过该参数可以限制用户对识别得到的结果进行编辑。（如：身份证号的识别都是准确的，可以限制用户不能编辑此字段）。\n" +
            "目前可以选为不可修改的字段有 姓名（idcard_name），身份证号（idcard_number），身份证有效期（idcard_valid_date），签发机关（idcard_issued_by）。传递参数时用逗号隔开，可以同时设置多个参数。例子：idcard_uneditable_field=idcard_number,idcard_valid_date 表示身份证号和过期时间都不可编辑，如果尝试编辑则会提示需要重新拍摄。传入其他值或格式不对，本次调用将失败，返回错误码400（BAD_ARGUMENTS）。\n" +
            "本参数默认值为空，表示所有字段都可以编辑。\n" +
            "请注意，如果您设置了scene_id参数、或者在控制台里设置了默认的场景，它对应的场景配置里将覆盖您此处的设定。")
    private String idcardUneditableField;
    /**
     * idcard_mode = 0 时，idcard_name idcard_number 这两个参数必须传；在其他情况下可以不传，即使传递了也不会使用。
     */
    @ApiModelProperty("idcard_name， 需要KYC验证对象的姓名，使用UTF-8编码；")
    private String idcardName;
    @ApiModelProperty("idcard_number， 需要KYC验证对象的身份证号，也就是一个18位长度的字符串。")
    private String idcardNumber;

    @ApiModelProperty("决定对于image_ref[x]参数对应的图片，当检测不出人脸时，是否旋转90度、180度、270度后再检测人脸。本参数取值只能是 “1” 或 \"0\" （缺省值为“0”）:")
    private String multiOrientedDetection;

    @ApiModelProperty("决定在活体检测结果中，是否进行云端假脸攻击判断，默认值为1。\n" +
            " 1：在活体检测结果中，不进行云端假脸攻击判断（合成脸、面具攻击、屏幕翻拍），但返回假脸攻击具体分数。您可以根据自身业务需要，结合云端假脸攻击具体分数设计后续流程。\n" +
            " 0：在活体检测结果中，判断云端假脸攻击情况。\n" +
            "当您选择静默活体（procedure_type==\"still\"）作为验证方式时建议此参数设置为“0”。\n" +
            "注：活体检测结果，请参见“Lite-GetResult”返回值的“liveness_result”段落。")
    private String fmpMode;
}
