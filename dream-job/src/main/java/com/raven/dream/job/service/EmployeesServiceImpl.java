package com.raven.dream.job.service;

import com.raven.dream.job.convert.EmployeesConverter;
import com.raven.dream.job.domain.entity.Employees;
import com.raven.dream.job.domain.entity.EmployeesTarget;
import com.raven.dream.job.mapper.EmployeesMapper;
import com.raven.dream.job.mapper.EmployeesTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2024/8/1 16:15
 *
 * @author longjiaocao
 */
@Service
public class EmployeesServiceImpl implements EmployeesService{

    @Autowired
    private EmployeesMapper employeesMapper;

    @Autowired
    private EmployeesTargetMapper employeesTargetMapper;

    @Autowired
    private EmployeesConverter employeesConverter;


    private List<EmployeesTarget> employeesTargetList;

    @PostConstruct
    public void init() {
        List<Employees> employees = employeesMapper.selectAll();
        employeesTargetList = employees.stream()
                .map(employeesConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void insertEmployees() {
        measureExecutionTimeInSeconds(() -> employeesTargetList.forEach(employeesTargetMapper::insert));
    }

    @Override
    public void batchInsertEmployees() {
        measureExecutionTimeInSeconds(() -> employeesTargetMapper.insertList(employeesTargetList));
    }

    @Override
    public void bulkInsertEmployeesInBatches() {
        List<List<EmployeesTarget>> batchList = splitList(employeesTargetList, 1000);
        measureExecutionTimeInSeconds(() -> {
            batchList.forEach(batch -> employeesTargetMapper.insertList(batch));
        });
    }

    @Override
    public void batchInsertEmployeesByFile() {


    }

    /**
     * 计算某个操作的执行时间（以秒为单位）。
     *
     * @param task 要执行的操作
     * @return 操作的执行时间（以秒为单位）
     */
    public static void measureExecutionTimeInSeconds(Runnable task) {
        long startTime = System.nanoTime(); // 获取开始时间（纳秒）
        task.run(); // 执行操作
        long endTime = System.nanoTime(); // 获取结束时间（纳秒）
        double timeTaken = (endTime - startTime) / 1_000_000_000.0;// 计算耗时（秒）
        System.out.println("耗时: " + timeTaken + " 秒");
    }

    /**
     * 将一个列表按指定大小分割成多个子列表。
     *
     * @param originalList 原始列表
     * @param chunkSize    每个子列表的大小
     * @param <T>          列表中元素的类型
     * @return 按指定大小分割的子列表列表
     */
    public static <T> List<List<T>> splitList(List<T> originalList, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        int listSize = originalList.size();
        if (chunkSize <= 0 || listSize == 0) {
            return chunks; // 返回空列表
        }

        for (int i = 0; i < listSize; i += chunkSize) {
            chunks.add(new ArrayList<>(originalList.subList(i, Math.min(listSize, i + chunkSize))));
        }

        return chunks;
    }
}