package com.raven.dream.job;

import com.raven.dream.job.service.EmployeesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DreamJobApplicationTests {

    @Autowired
    private EmployeesService employeesService;

    @Test
    void insertEmployees() {
        employeesService.insertEmployees();
    }

    @Test
    void batchInsertEmployees() {
        employeesService.batchInsertEmployees();
    }


    @Test
    void bulkInsertEmployeesInBatches() {
        employeesService.bulkInsertEmployeesInBatches();
    }
}
