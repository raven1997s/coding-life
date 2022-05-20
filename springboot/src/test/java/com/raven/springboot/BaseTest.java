package com.raven.springboot;

import com.edaijia.edipao.SysMain;
import com.edaijia.edipao.application.utils.EnvHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * Description: 
 * date: 2021/11/18 11:22
 * @author YeJiang
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SysMain.class)
@AutoConfigureMockMvc
@Slf4j
public class BaseTest extends ApplicationObjectSupport {

    static {
        EnvHelper.setActive("dev");
    }

    @PostConstruct
    public void init() {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) getApplicationContext();
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String active = StringUtils.arrayToCommaDelimitedString(activeProfiles);
    }
}
