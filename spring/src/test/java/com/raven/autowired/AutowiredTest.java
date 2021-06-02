package com.raven.autowired;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @PackageName: com.raven.autowired
 * @ClassName: AutowiredTest
 * @Blame: raven
 * @Date: 2021-06-01 10:46
 * @Description: 总结：
 * 一切框架设计都要必须考虑扩展性！！！
 * 1.抽象
 * 2.设计模式 ：设计模式是前人总结出的能够更好解决问题的一套思想。
 * <p>
 * spring 做出的扩展
 * 1.在创建对象之前可以让你做一些事情
 * 2.容器初始化之前可以做一些事情
 * 3.在不同的阶段发出不同的事件，你还可以做一些事情
 * 4.抽象出各个接口 ，满足搞拓展性
 * 5.面向接口编程
 */
public class AutowiredTest {

    /**
     * 未设置程序访问修饰符
     *
     * @throws Exception
     */
    @Test
    public void autowiredTest() throws Exception {
        PersonController personController = new PersonController();
        Class<? extends PersonController> clazz = personController.getClass();
        // 通过clazz.getDeclaredFields() 可以拿到所有的字段属性信息
        //  Field[] declaredFields = clazz.getDeclaredFields();
        //  Arrays.asList(declaredFields).forEach(System.out::println);

        // 已知属性名 personService
        // 直接通过clazz.getDeclar edField(filedName) 获取personService字段对象
        Field personServiceFiled = clazz.getDeclaredField("personService");
        // 给personController的personService字段设置属性值
        PersonService personService = new PersonService();
        personService.setServiceName("毁灭人类服务");
        personServiceFiled.set(personController, personService);
        System.out.println(personController.getPersonService());

        /**
         * java.lang.IllegalAccessException: Class com.raven.autowired.AutowiredTest can not access a member of class com.raven.autowired.PersonController with modifiers "private"
         * 以上程序会报错 因为personService为PersonController私有的方法
         *
         * jvm是怎么知道我们的personService成员变量为私有的呢？
         * 当程序编译时 jvm会在字节码层面 将访问修饰符放到方法的描述符下面的flags: ACC_PUBLIC, ACC_SUPER 具体编译过程见com.raven.autowired.BuildClass
         */
    }


    /**
     * 设置程序访问修饰符
     *
     * @throws Exception
     */
    @Test
    public void autowiredTest2() throws Exception {
        PersonController personController = new PersonController();
        Class<? extends PersonController> clazz = personController.getClass();
        // 通过clazz.getDeclaredFields() 可以拿到所有的字段属性信息
        //  Field[] declaredFields = clazz.getDeclaredFields();
        //  Arrays.asList(declaredFields).forEach(System.out::println);

        // 已知属性名 personService
        // 直接通过clazz.getDeclaredField(filedName) 获取personService字段对象
        Field personServiceFiled = clazz.getDeclaredField("personService");
        // 设置访问私有程序权限
        personServiceFiled.setAccessible(true);
        // 给personController的personService字段设置属性值
        PersonService personService = new PersonService();
        personService.setServiceName("毁灭人类服务");
        personServiceFiled.set(personController, personService);
        System.out.println(personController.getPersonService());

    }

    /**
     * 设置程序访问修饰符后
     * 通过反射手动get set 设置属性值
     *
     * @throws Exception
     */
    @Test
    public void autowiredTest3() throws Exception {
        PersonController personController = new PersonController();
        Class<? extends PersonController> clazz = personController.getClass();
        // 通过clazz.getDeclaredFields() 可以拿到所有的字段属性信息
        //  Field[] declaredFields = clazz.getDeclaredFields();
        //  Arrays.asList(declaredFields).forEach(System.out::println);

        // 已知属性名 personService
        // 直接通过clazz.getDeclaredField(filedName) 获取personService字段对象
        Field personServiceFiled = clazz.getDeclaredField("personService");
        // 设置访问私有程序权限
        personServiceFiled.setAccessible(true);
        // 给personController的personService字段设置属性值
        PersonService personService = new PersonService();
        personService.setServiceName("毁灭人类服务");

        // 手动构建setMethodName
        String filedName = personServiceFiled.getName();
        filedName = filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        String setFiledName = "set" + filedName;

        // 获取指定的personServiceFiled 的method对象
        Method method = clazz.getMethod(setFiledName, PersonService.class);
        // 执行personController 的set方法 将personService 设置给personController
        method.invoke(personController, personService);
        System.out.println(personController.getPersonService());

    }


    /**
     * 设置程序访问修饰符后
     * 通过自定义注解 Autowired注入属性
     *
     * @throws Exception
     */
    @Test
    public void autowiredTest4() throws Exception {
        PersonController personController = new PersonController();
        Class<? extends PersonController> clazz = personController.getClass();
        // 通过clazz.getDeclaredFields() 可以拿到所有的字段属性信息
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.asList(declaredFields).forEach(field -> {
            // 当字段上有AutoWired注解时 创建该实例对象 并设置值给PersonController，进行属性注入操作
            AutoWired annotation = field.getAnnotation(AutoWired.class);
            if (Objects.nonNull(annotation)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                try {
                    Object o = type.getConstructor().newInstance();
                    field.set(personController, o);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(personController.getPersonService());
        System.out.println(personController);

    }

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Class<?> cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[]) c.get(cache);
        array[130] = array[129];
        array[131] = array[129];
        Integer a = 1;
        if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) {
            System.out.println("succcess");
        }
    }

}
