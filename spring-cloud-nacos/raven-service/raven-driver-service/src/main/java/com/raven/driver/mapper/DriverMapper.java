package com.raven.driver.mapper;

import com.raven.driver.entity.DriverEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @PackageName: com.raven.driver.mapper
 * @ClassName: DriverMapper
 * @Blame: raven
 * @Date: 2021-07-08 15:49
 * @Description:
 */
@Repository
public interface DriverMapper extends BaseMapper<DriverEntity> {

    DriverEntity findById(int id);

}