package com.raven.driver.mapper;

import com.raven.driver.entity.DriverEntity;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @PackageName: com.raven.driver.mapper
 * @ClassName: DriverMapper
 * @Blame: raven
 * @Date: 2021-07-08 15:49
 * @Description:
 */
@Mapper
public interface DriverMapper extends BaseMapper<DriverEntity> {

    DriverEntity findById(int id);

}