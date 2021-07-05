package com.raven.nacos.config.nacosconfig;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 添加 @EnableNacosConfig 注解启用 Nacos Spring 的配置管理服务。
 */
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
/**
 * 通过添加 @EnableNacosDiscovery 注解开启 Nacos Spring 的服务发现功能：
 */
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
/**
 * Document: https://nacos.io/zh-cn/docs/quick-start-spring.html
 * <p>
 * Nacos 控制台添加配置：
 * <p>
 * Data ID：example
 * <p>
 * Group：DEFAULT_GROUP
 * <p>
 * 配置内容：useLocalCache=true
 *
 *
 * 以下示例中，我们使用 @NacosPropertySource 加载了 dataId 为 example 的配置源，并开启自动更新
 *
 */
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosConfiguration {

}