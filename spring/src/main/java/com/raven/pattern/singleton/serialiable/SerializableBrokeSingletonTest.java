package com.raven.pattern.singleton.serialiable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @PackageName: com.raven.pattern.singleton.serialiable
 * @ClassName: SerializableBrokeSingletonTest
 * @Blame: raven
 * @Date: 2021-06-20 9:43
 * @Description: 模拟反序列化破坏单例设计
 */
public class SerializableBrokeSingletonTest {

    public static void main(String[] args) throws Exception {
        SerializableSingleton s1 = null;
        SerializableSingleton s2 = SerializableSingleton.getInstance();

        // 将s2写到本地磁盘
        FileOutputStream fos = new FileOutputStream("SerializableSingleton.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s2);
        oos.flush();
        oos.close();

        // 将s2从磁盘读取
        FileInputStream fis = new FileInputStream("SerializableSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (SerializableSingleton) ois.readObject();
        ois.close();

        // 发现单例被反序列化所破坏 需要在单例中重写 readResolve防止序列化破坏单例
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
