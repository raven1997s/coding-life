package com.raven.driver.feign;

import com.raven.driver.entity.DriverEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PackageName: com.raven.driver.feign
 * @ClassName: DriverFeign
 * @Blame: raven
 * @Date: 2021-07-07 17:19
 * @Description:
 * FeignClient注解: 该接口对外提供远程调用,远程调用服务名称为driver
 * 调用地址为类上的RequestMapping + 方法上的GetMapping
 */
@FeignClient("driver")
@RequestMapping("/driver")
public interface DriverFeign {

    @GetMapping("/get/{id}")
    public DriverEntity getOne(@PathVariable(value = "id") int id);
}
