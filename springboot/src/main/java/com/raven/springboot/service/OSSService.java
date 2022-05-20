package com.raven.springboot.service;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * oss服务
 * 1.如果您所购买的ECS实例与OSS Bucket所在地域(region)相同，那么推荐您通过内网的方式访问OSS，不收取流量费用
 * 2.通过外网访问OSS服务<Schema>://<Bucket>.<外网Endpoint>/<Object>
 * 3.OSS开通Region和Endpoint对照表:去oss管理后台查看
 *
 * @author zhoutao
 * @date 2018/12/13
 */
@Service
@Slf4j
public class OSSService {
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.publicAccessDomain}")
    private String publicAccessDomain;
    @Value("${aliyun.oss.internalEndpoint}")
    private String internalEndpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${spring.profiles.active}")
    private String env;

    private OSSClient ossClient;

    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 上传文件
     *
     * @param module - 模块标识,如果含有子模块用/分隔,比如shop/stock
     * @param file   - 原始文件
     * @return - 返回完整的访问路径
     */
    public String upload(String module, File file) {
        StringBuilder key = new StringBuilder();

        //顶级目录用环境标识避免覆盖
        key.append(env);
        key.append("/edipao/");
        key.append(module);
        key.append("/");
        key.append(file.getName());

        //上传到阿里云oss
        ossClient.putObject(bucketName, key.toString(), file);
        String publicAccessUrl = publicAccessDomain + "/" + key.toString();
        return publicAccessUrl;
    }

    /**
     * 上传文件
     *
     * @param module   - 模块标识,如果含有子模块用/分隔,比如shop/stock
     * @param fileName - 文件名称
     * @param file     - 原始文件流
     * @return - 返回完整的访问路径
     */
    public String upload(String module, String fileName, MultipartFile file) throws IOException {
        StringBuilder key = new StringBuilder();

        //顶级目录用环境标识避免覆盖
        key.append(env);
        key.append("/edipao/");
        key.append(module);
        key.append("/");
        key.append(fileName);
        final String extensionName = getExtensionName(file.getOriginalFilename());
        if (StringUtils.isNotBlank(extensionName)) {
            key.append(".");
            key.append(extensionName);
        }

        //上传到阿里云oss
        ossClient.putObject(bucketName, key.toString(), file.getInputStream());
        String publicAccessUrl = publicAccessDomain + "/" + key.toString();
        return publicAccessUrl;
    }

    /**
     * 上传文件
     *
     * @param module   - 模块标识,如果含有子模块用/分隔,比如shop/stock
     * @param fileName - 文件名称
     * @param bytes    - 原始文件流
     * @return - 返回完整的访问路径
     */
    public String upload(String module, String fileName, byte[] bytes) {
        StringBuilder key = new StringBuilder();

        //顶级目录用环境标识避免覆盖
        key.append(env);
        key.append("/edipao/");
        key.append(module);
        key.append("/");
        key.append(fileName);

        //上传到阿里云oss
        ossClient.putObject(bucketName, key.toString(), new ByteArrayInputStream(bytes));
        String publicAccessUrl = publicAccessDomain + "/" + key.toString();
        return publicAccessUrl;
    }

    /**
     * 下载oss文件到一个指定的本地文件
     *
     * @param fileUrl - 文件完整访问地址
     * @param file
     */
    public void download(String fileUrl, String file) {
        String key = fileUrl.substring(fileUrl.indexOf(env));
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata objectMetadata = ossClient.getObject(getObjectRequest, new File(file));
        log.info("ossService download success, fileUrl={}, getLastModified={}", fileUrl, objectMetadata.getLastModified());
    }

    @PostConstruct
    void init() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setRequestTimeoutEnabled(true);
        clientConfiguration.setRequestTimeout(30000);
        this.ossClient = new OSSClient(internalEndpoint, accessKeyId, accessKeySecret, clientConfiguration);
    }

    @PreDestroy
    void destory() {
        this.ossClient.shutdown();
    }
}
