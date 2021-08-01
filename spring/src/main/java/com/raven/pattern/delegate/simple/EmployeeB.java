package com.raven.pattern.delegate.simple;

/**
 * @PackageName: com.raven.pattern.delegate.simple
 * @ClassName: EmployeeB
 * @Blame: raven
 * @Date: 2021-08-01 10:39
 * @Description:
 */
public class EmployeeB  implements  IEmployee{

    @Override
    public void doing(String command){
        System.out.println("我是员工B，我擅长GO,我执行"  + command + "命令");
    }
}
