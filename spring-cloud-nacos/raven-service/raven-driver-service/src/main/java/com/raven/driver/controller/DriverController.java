package com.raven.driver.controller;

import com.raven.driver.entity.DriverEntity;
import com.raven.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName: com.raven.driver.controller
 * @ClassName: DriverController
 * @Blame: raven
 * @Date: 2021-07-08 15:48
 * @Description:
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @GetMapping("/get/{id}")
    public DriverEntity getOne(@PathVariable(value = "id") int id) {
        return driverService.findById(id);
    }

    @GetMapping("/get2/{id}")
    public DriverEntity getOne2(@PathVariable(value = "id") int id){
        return driverService.findById(id);
    }
}

