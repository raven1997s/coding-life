package com.raven.pattern.delegate.simple;

/**
 * @PackageName: com.raven.pattern.delegate.simple
 * @ClassName: EmployeeA
 * @Blame: raven
 * @Date: 2021-08-01 10:39
 * @Description:
 */
public class EmployeeA implements IEmployee{
    @Override
    public void doing(String command) {
        System.out.println("我是员工，A我擅长JAVA，我执行" + command + "命令");
    }
}
