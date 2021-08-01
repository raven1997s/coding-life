package com.raven.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.raven.pattern.delegate.simple
 * @ClassName: Leader
 * @Blame: raven
 * @Date: 2021-08-01 10:42
 * @Description: leader知道每一个员工所擅长的语言，当有需求到达时，根据需求要求的语言，交于不同的员工进行开发任务。
 */
public class Leader {

    private static Map<String, IEmployee> employeeMap = new HashMap<>();

    private static String JAVA = "JAVA";
    private static String GO = "GO";
    private static String DEFAULT = JAVA;

    static {
        employeeMap.put(JAVA, new EmployeeA());
        employeeMap.put(GO, new EmployeeB());
    }

    public void doCommand(String command) {
        if (!employeeMap.containsKey(command)) {
            employeeMap.get(DEFAULT).doing(command);
        } else {
            employeeMap.get(command).doing(command);
        }
    }
}
