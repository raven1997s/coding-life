package com.raven.pattern.factory.abstractfactory;

/**
 * @PackageName: com.raven.pattern.factory.abstractfactory
 * @ClassName: JavaSource
 * @Blame: raven
 * @Date: 2021-06-17 20:57
 * @Description:
 */
public class JavaSource implements ISource {

    @Override
    public void print() {
        System.out.println("打印java源码");
    }
}
