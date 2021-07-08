package com.raven.consumer.controller;

import com.raven.driver.entity.DriverEntity;
import com.raven.driver.feign.DriverFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @PackageName: com.raven.consumer.controller
 * @ClassName: ConsumerController
 * @Blame: raven
 * @Date: 2021-07-08 18:09
 * @Description:
 */
@RestController()
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private DriverFeign driverFeign;

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String findDriverName(@PathVariable int id) {
        DriverEntity driverEntity = driverFeign.getOne(id);
        return Objects.nonNull(driverEntity) ? driverEntity.getName() : "查无此人";
    }
}
