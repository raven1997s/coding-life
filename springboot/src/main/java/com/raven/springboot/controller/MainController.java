package com.raven.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.raven.springboot.controller
 * @ClassName: MainController
 * @Blame: raven
 * @Date: 2021-05-28 17:23
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/list")
    public Map<String,String> mainTest(){
        Map<String,String> map = new HashMap<>();
        map.put("1","a");
        map.put("2","a");
        map.put("3","a");
        return map;
    }
}
