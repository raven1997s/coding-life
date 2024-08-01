package com.raven.dream.job.convert;

import com.raven.dream.job.domain.entity.Employees;
import com.raven.dream.job.domain.entity.EmployeesTarget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2024/8/1 16:19
 *
 * @author longjiaocao
 */
@Mapper(componentModel = "spring")
@Component
public interface EmployeesConverter {

    @Mapping(target = "id", ignore = true)
    EmployeesTarget convert(Employees employees);
}