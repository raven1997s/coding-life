package com.raven.selenium.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.raven.selenium.dto.CookieDTO;
import com.raven.selenium.ocr.AccurateBasic;
import com.raven.selenium.ocr.util.CalculateUtil;
import com.raven.selenium.ocr.util.HttpClientUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.Map;
import java.util.Objects;
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
    public static final String NOTIFY_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/abe350fa-9b86-413e-81ed-d787de407078";
    public static int YZM_ERROR = -9999;
    public static final String LOGIN_URL = "https://dms.cnhtcerp.com:1443/#/login";
    public static final String YZM_XPATH = "//*[@id=\"app\"]/section/main/div/form/div[3]/div/div[2]/img";
    public static final String YZM_INPUT_XPATH = "//*[@id=\"app\"]/section/main/div/form/div[3]/div/div[1]/input";
    public static final String LOGIN_BUTTON = "//*[@id=\"app\"]/section/main/div/form/div[4]/div/button";
    public static final String REDIS_KEY = "AUTO_LOGIN";
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void autoLogin(String baseName, String username, String password) {
        WebDriver driver = null;
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
                driver = new ChromeDriver(chromeOptions);
                //driver = new ChromeDriver();
                driver.get(LOGIN_URL);
                // 销服一线通
                log.info(baseName + "title of current page is {}, ready to login !", driver.getTitle());
                while (driver.getTitle().contains("销服一线通")) {
                    // 输入账户密码
                    driver.findElement(By.name("username")).clear();
                    driver.findElement(By.name("username")).sendKeys(username);
                    driver.findElement(By.name("password")).clear();
                    driver.findElement(By.name("password")).sendKeys(password);
                    // 获取验证码
                    int result = getYzm(driver);
                    // 填写验证码
                    driver.findElement(By.xpath(YZM_INPUT_XPATH)).clear();
                    driver.findElement(By.xpath(YZM_INPUT_XPATH)).sendKeys(String.valueOf(result));
                    // 登录
                    driver.findElement(By.xpath(LOGIN_BUTTON)).click();
                    TimeUnit.SECONDS.sleep(5);
                }

                // 中国重汽一线通 - 首页  登录成功发送cookie到redis
                log.info(baseName + "title of current page is {}，login success ", driver.getTitle());
                String loginMSg = baseName + " 登录成功";
                HttpClientUtil.getInstance().sendHttpPostJson(NOTIFY_URL, buildJsonParams(loginMSg));

                Cookie cookie = driver.manage().getCookieNamed("jwt");
                if (cookie == null) {
                    break;
                }
                redisTemplate.opsForHash().put(REDIS_KEY, baseName, JSON.toJSONString(CookieDTO.builder().name(cookie.getName()).value(cookie.getValue()).build()));
                log.info(REDIS_KEY + baseName + "cookie [ name:" + cookie.getName() + "  value:" + cookie.getValue() + " ]");

                while (driver.getTitle().contains("中国重汽一线通")) {
                    // 每隔一段时间刷新页面
                    driver.navigate().refresh();
                    //TimeUnit.SECONDS.sleep(10);
                    TimeUnit.MINUTES.sleep(RandomUtils.nextInt(5, 10));
                }

                // token被踢
                String errorMsg = baseName + " cookie失效，重新登录";
                HttpClientUtil.getInstance().sendHttpPostJson(NOTIFY_URL, buildJsonParams(errorMsg));
            } catch (Exception e) {
                // 代码问题一直无法登录 重试10次
                if (retryCount-- < 0) {
                    retryFlag = false;
                }
                String errorMsg = baseName + "登录异常,剩余重试次数为" + retryCount;
                log.error(errorMsg, e);
                HttpClientUtil.getInstance().sendHttpPostJson(NOTIFY_URL, buildJsonParams(errorMsg + JSON.toJSONString(e)));
            } finally {
                if (driver != null){
                    driver.close();
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
        return result;
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

    //组装请求参数
    public static String buildJsonParams(String msgStr) {
        Map<String, Object> contentMap = Maps.newHashMapWithExpectedSize(1);
        contentMap.put("text", msgStr);

        Map<String, Object> messageMap = Maps.newHashMapWithExpectedSize(2);
        messageMap.put("msg_type", "text");
        messageMap.put("content", contentMap);
        return JSON.toJSONString(messageMap);
    }
}