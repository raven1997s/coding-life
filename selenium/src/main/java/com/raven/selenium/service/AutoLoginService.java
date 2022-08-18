package com.raven.selenium.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.raven.selenium.ocr.AccurateBasic;
import com.raven.selenium.ocr.util.CalculateUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * date: 2022/8/18 17:06
 *
 * @author raven
 */
@Slf4j
@Component
public class AutoLoginService {

    public static int YZM_ERROR = -9999;
    public static final String LOGIN_URL = "https://dms.cnhtcerp.com:1443/#/login";
    public static final String YZM_XPATH = "//*[@id=\"app\"]/section/main/div/form/div[3]/div/div[2]/img";
    public static final String YZM_INPUT_XPATH = "//*[@id=\"app\"]/section/main/div/form/div[3]/div/div[1]/input";
    public static final String LOGIN_BUTTON = "//*[@id=\"app\"]/section/main/div/form/div[4]/div/button";

    public void autoLogin(String username, String password) {
        int retryCount = 10;
        boolean retryFlag = true;
        // cookie被踢了或者发送异常 重新登录
        while (retryFlag) {
            try {
                WebDriverManager.chromedriver().setup();
                // 隐藏式启动
                ChromeOptions chromeOptions = new ChromeOptions();
                //设置 chrome 的无头模式，没有gui的时候必须要设置
                //很关键
                chromeOptions.addArguments("headless");
                //很关键
                chromeOptions.addArguments("no-sandbox");
                chromeOptions.addArguments("allow-running-insecure-content");

                //打开Chrome浏览器
                WebDriver driver = new ChromeDriver();
                driver.get(LOGIN_URL);
                // 打开页面后等待一会
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                // 中国重汽一线通 - 首页
                log.info("title of current page is {}", driver.getTitle());

                // 输入账户密码
                driver.findElement(By.name("username")).sendKeys(username);
                driver.findElement(By.name("password")).sendKeys(password);
                // 获取验证码
                int result = getYzm(driver);
                // 填写验证码
                driver.findElement(By.xpath(YZM_INPUT_XPATH)).sendKeys(String.valueOf(result));
                // 登录
                driver.findElement(By.xpath(LOGIN_BUTTON)).click();

                TimeUnit.SECONDS.sleep(3);
                // 销服一线通  登录成功发送cookie
                String homePage = driver.getTitle();
                log.info("title of current page is {}，login success ", homePage);
                Set<Cookie> cookies = driver.manage().getCookies();
                for (Cookie cookie : cookies) {
                    log.info("name:" + cookie.getName() + "  value:" + cookie.getValue());
                }

                TimeUnit.SECONDS.sleep(3);
                while (homePage.contains("销服一线通")) {
                    // 每隔一段时间刷新页面
                    driver.navigate().refresh();
                    TimeUnit.MINUTES.sleep(RandomUtils.nextInt(5, 10));
                }

                // token被踢
                driver.close();

            } catch (Exception e) {
                // 代码问题一直无法登录 重试10次
                if (retryCount-- < 0) {
                    retryFlag = false;
                }
            }
        }
    }


    private int getYzm(WebDriver driver) throws InterruptedException {

        int result = YZM_ERROR;
        String response;
        // 验证码解析失败重新获取
        while (result == YZM_ERROR) {
            // 获取验证码元素
            WebElement yzmElement = driver.findElement(By.xpath(YZM_XPATH));
            // 获取验证码图片文字信息
            response = AccurateBasic.accurateBasic(base64ToByteArray(yzmElement.getAttribute("src")));
            // 解析计算验证码结果
            result = parseOcrResult(response);
            if (result == YZM_ERROR) {
                yzmElement.click();
            }
            TimeUnit.SECONDS.sleep(3);
        }
        return 0;
    }

    private int parseOcrResult(String response) {
        JSONObject jsonObject = JSON.parseObject(response);
        //
        Integer errorCode = jsonObject.getInteger("error_code");
        if (Objects.nonNull(errorCode)) {
            return YZM_ERROR;
        }

        JSONArray wordsResult = jsonObject.getJSONArray("words_result");
        if (CollectionUtils.isEmpty(wordsResult)) {
            return YZM_ERROR;
        }
        JSONObject wordsJsonObject = wordsResult.getJSONObject(0);
        if (Objects.isNull(wordsJsonObject)) {
            return YZM_ERROR;
        }

        String words = wordsJsonObject.getString("words");

        if (words.length() < 3) {
            return YZM_ERROR;
        }

        return parseResult(words);
    }

    private byte[] base64ToByteArray(String base64Str) {
        String imgBase64 = base64Str.replace("data:image/png;base64,", "");
        return Base64.getDecoder().decode(imgBase64);
    }

    private static int parseResult(String words) {
        String s = "^[0-9][-+*÷][0-9]";
        Pattern p = Pattern.compile(s);
        Matcher matcher = p.matcher(words);
        if (matcher.find()) {
            return CalculateUtil.calculate(matcher.group());
        }
        return YZM_ERROR;
    }
}