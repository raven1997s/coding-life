package com.raven.pattern.delegate.simple;

/**
 * @PackageName: com.raven.pattern.delegate.simple
 * @ClassName: IEmplyee
 * @Blame: raven
 * @Date: 2021-08-01 10:44
 * @Description: 定义员工接口 规范员工行为
 */
public interface IEmployee {

    /**
     * 员工可以干活
     * @param command 命令行为
     */
    void doing(String command);
}
