package com.raven.nacos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @PackageName: com.raven.nacos.service
 * @ClassName: NacosConfigService
 * @Blame: raven
 * @Date: 2021-07-09 20:34
 * @Description:
 */
@Service
public class NacosConfigService {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;


    public String getConfig(String dataId, String group, long timeoutMs) {
//        String content ="";
//        try {
//            Properties properties = new Properties();
//            properties.put("serverAddr", serverAddr);
//            ConfigService configService = NacosFactory.createConfigService(properties);
//            content = configService.getConfig(dataId, group, timeoutMs);
//            System.out.println(content);
//        } catch (NacosException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return "";
    }
}
