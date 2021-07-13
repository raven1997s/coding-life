package com.raven.nacos.controller;

import com.raven.nacos.service.NacosConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Autowired
    private NacosConfigService nacosConfigService;
    @Value(value = "${demo.data-id-name}")
    private String dataIdName;
    @Value(value ="${demo.group-name}")
    private String groupName;
    @Value(value ="${demo.env}")
    private String env;

    @RequestMapping("/get")
    public String getDev() {
       return dataIdName;
    }

}