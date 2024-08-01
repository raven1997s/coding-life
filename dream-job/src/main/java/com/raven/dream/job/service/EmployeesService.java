package com.raven.dream.job.service;

/**
 * Description:
 * date: 2024/8/1 16:14
 *
 * @author longjiaocao
 */
public interface EmployeesService {

    /**
     * 逐条插入10万条数据
     */
    void insertEmployees();

    /**
     * 批量一次性插入10万条数据
     */
    void batchInsertEmployees();

    /**
     * 批量分批插入10万条数据
     */
    void bulkInsertEmployeesInBatches();

    void batchInsertEmployeesByFile();
}