package com.raven.springboot.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.raven.springboot.common.properties
 * @ClassName: MegviiProperties
 * @Blame: raven
 * @Date: 2021-08-02 18:33
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "megvii")
@Component
public class MegviiProperties {
    private String secret;
    private String appKey;
    private String returnUrl;
    private String notifyUrl;
    private String webTitle;

}
