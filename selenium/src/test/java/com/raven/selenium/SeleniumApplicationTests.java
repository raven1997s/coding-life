package com.raven.selenium;

import com.alibaba.fastjson.JSON;
import com.raven.selenium.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SeleniumApplicationTests {
    public static final String LOGIN_KEY = "AUTO_LOGIN_DIPAO_LOGIN";
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        LoginDTO dto = new LoginDTO();
        dto.setDomainName("https://api.d.edipao.cn");
        dto.setPhone("18829223750");
        dto.setPassWord("123456");
        dto.setProfiles("test");
        redisTemplate.opsForHash().put(LOGIN_KEY,dto.getProfiles(), JSON.toJSONString(dto));

        LoginDTO dto1 = new LoginDTO();
        dto1.setDomainName("https://api.edipao.cn");
        dto1.setPhone("19999999999");
        dto1.setPassWord("999Edj999");
        dto1.setProfiles("prod");
        redisTemplate.opsForHash().put(LOGIN_KEY,dto1.getProfiles(), JSON.toJSONString(dto1));
    }

}
