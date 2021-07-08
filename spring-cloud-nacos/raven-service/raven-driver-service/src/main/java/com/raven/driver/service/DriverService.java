package com.raven.driver.service;

import com.raven.driver.entity.DriverEntity;
import com.raven.driver.mapper.DriverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName: com.raven.driver.service
 * @ClassName: DriverService
 * @Blame: raven
 * @Date: 2021-07-08 15:49
 * @Description:
 */
@Service
public class DriverService {

    @Autowired
    private DriverMapper driverMapper;

    public DriverEntity findById(int id){
        return driverMapper.findById(id);
    }
}
