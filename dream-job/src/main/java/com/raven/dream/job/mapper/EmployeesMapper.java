package com.raven.dream.job.mapper;

import com.raven.dream.job.common.mabatis.MyMapper;
import com.raven.dream.job.domain.entity.Employees;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesMapper extends MyMapper<Employees> {
}

