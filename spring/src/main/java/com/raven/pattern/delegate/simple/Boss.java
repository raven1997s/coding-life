package com.raven.pattern.delegate.simple;

/**
 * @PackageName: com.raven.pattern.delegate.simple
 * @ClassName: Boss
 * @Blame: raven
 * @Date: 2021-08-01 10:42
 * @Description:
 */
public class Boss {

    public void execute(String command , Leader leader){
        leader.doCommand(command);
    }
}
