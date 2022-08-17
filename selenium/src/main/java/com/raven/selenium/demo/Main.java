package com.raven.selenium.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2022/8/17 17:07
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        //打开Chrome浏览器
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://dms.cnhtcerp.com:1443/#/login");
        webDriver.findElement(By.name("username")).sendKeys("");
        webDriver.findElement(By.name("password")).sendKeys("");
        WebElement element = webDriver.findElement(By.name("username"));
        //TimeUnit.SECONDS.sleep(5);
        ////打开百度网站
        //TimeUnit.SECONDS.sleep(2);
        ////输入框输入搜索关键词 selenium 中文官网
        //webDriver.findElement(By.id("kw")).sendKeys("selenium 中文官网");
        //TimeUnit.SECONDS.sleep(2);
        ////点击百度一下按钮
        //webDriver.findElement(By.id("su")).submit();
        //TimeUnit.SECONDS.sleep(2);
        ////查询所有搜索的结果
        //List<WebElement> resultElements = webDriver.findElements(By.className("result"));
        //if (!resultElements.isEmpty()) {
        //    //找到第一条结果的第一个链接
        //    List<WebElement> aTagElements = resultElements.get(0).findElements(By.tagName("a"));
        //    if (!aTagElements.isEmpty()) {
        //        //新开一个窗口打开此链接
        //        String href = aTagElements.get(0).getAttribute("href");
        //        System.out.println(href);
        //        ((JavascriptExecutor) webDriver).executeScript(String.format("window.open('%s')", href));
        //    }
        //}
        ////关闭浏览器
        boolean flag = true;
        while (flag){
            TimeUnit.SECONDS.sleep(10);
            flag = false;
        }
        webDriver.quit();
        //driver.quit();

    }
}