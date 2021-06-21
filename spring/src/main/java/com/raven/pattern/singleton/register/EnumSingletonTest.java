package com.raven.pattern.singleton.register;

import java.lang.reflect.Constructor;

/**
 * @PackageName: com.raven.pattern.singleton.register
 * @ClassName: EnumSingletonTest
 * @Blame: raven
 * @Date: 2021-06-21 14:16
 * @Description: 验证枚举注册式单例模式在JDK层面 防止反射破坏单例设计 防止反序列化破坏单例设计
 */
public class EnumSingletonTest {

    /**
     * 防止反序列化破坏单例设计
     *
     * @param args
     */
//    public static void main(String[] args) throws Exception {
//        EnumSingleton s1 = null;
//        EnumSingleton s2 = EnumSingleton.getInstance();
//
//        // 将s2写到本地磁盘
//        FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(s2);
//        oos.flush();
//        oos.close();
//
//        // 将s2从磁盘读取
//        FileInputStream fis = new FileInputStream("EnumSingleton.obj");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        s1 = (EnumSingleton) ois.readObject();
//        ois.close();
//
////       虽然我们没有重写readResolve方法，但枚举式单例仍然可以防止反序列化破坏单例设计 主要是因为枚举式通过类的class对象+名字确定返回对象 确保唯一
////        Object obj = readObject0(false); ==》
////       checkResolve(readEnum(unshared)); ==》
////       Enum<?> en = Enum.valueOf((Class)cl, name);
//
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s1 == s2);
//    }

    /**
     * 防止反射破坏单例设计
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Class clazz = EnumSingleton.class;
        // 通过反编译EnumSingleton 的字节码文件发现，我们只能够通过有参构造创建EnumSingleton对象
        Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
        c.setAccessible(true);
        // jdk内部禁止通过反射创建枚举对象
//        if ((clazz.getModifiers() & Modifier.ENUM) != 0)
//            throw new IllegalArgumentException("Cannot reflectively create enum objects");
        // Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        EnumSingleton instance = (EnumSingleton) c.newInstance("test", 1);
        EnumSingleton instance1 = EnumSingleton.getInstance();
        System.out.println(instance == instance1);
    }
}
