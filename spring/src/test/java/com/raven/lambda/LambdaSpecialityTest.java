package com.raven.lambda;

import org.junit.Test;

/**
 * @PackageName: com.raven
 * @ClassName: LambdaSpecialityTest
 * @Blame: raven
 * @Date: 2021-06-07 20:22
 * @Description: lambda表达式的特性
 * 1.什么是lambda表达式
 * 2.参数传递的方式
 */
public class LambdaSpecialityTest {
    /**
     * lambda表达式的特性:
     * 必须是函数式接口
     */
    @Test
    public void speciality1() {
        // jkd1.8前
        executeFunctionalInterface(new MyFunctionalInterface() {
            @Override
            public void print() {
                System.out.println("run my function");
            }
        });

        System.out.println("==============");
        executeFunctionalInterface(() -> System.out.println("run my lambda function"));

    }

    private void executeFunctionalInterface(MyFunctionalInterface myFunctionalInterface) {
        myFunctionalInterface.print();
    }

    // 标识一个接口为函数式接口
    @FunctionalInterface
    public interface MyFunctionalInterface {
        void print();
    }

    ;

    //    ===============================================================================================

    /**
     * 标识一个接口为函数式接口
     *
     * @param <T>
     * @param <R>
     */
    @FunctionalInterface
    public interface CalcInterface<T, R> {
        // 计算:传入俩个参数 返回一个值
        R calc(T t1, T t2);
    }

    /**
     * 传入不同的行为执行不同的逻辑
     *
     * @param param         param
     * @param param2        param2
     * @param calcInterface calc逻辑
     * @return
     */
    private int calcMethod(int param, int param2, CalcInterface<Integer, Integer> calcInterface) {
        return calcInterface.calc(param, param2);
    }

    @Test
    public void speciality2() {
        int add = calcMethod(100, 100, Integer::sum);
        System.out.println(add);
        System.out.println("=======================");

        int sub = calcMethod(200, 200, (a, b) -> a - b);
        System.out.println(sub);

    }

//    ===============================================================================================


    /**
     * 定义一个函数式接口 定义一个方法规则
     */
    @FunctionalInterface
    public interface ReferenceInterface {
        void print(String type);
    }


    private static void doReferenceMethod(ReferenceInterface referenceInterface) {
        // 传递参数
        referenceInterface.print("静态方法方法引用");
    }

    private void doReferenceMethod2(ReferenceInterface referenceInterface) {
        // 传递参数
        referenceInterface.print("非静态方法方法引用");
    }


    /**
     * 参数的传递方式：
     * 1.可以是静态方法引用
     * 2.也可以是普通方法
     */
    @Test
    public void speciality3() {
        // 普通方法引用
        new LambdaSpecialityTest().doReferenceMethod2((System.out::println));

        // 在方法中传递接口的时候再进行定义该怎么玩 要怎么玩 想怎么玩
        doReferenceMethod((str -> System.out.println(String.format("自定义：%s", str))));
    }

    // 静态方法引用
    public static void main(String[] args) {
        LambdaSpecialityTest.doReferenceMethod(System.out::println);
    }

}
