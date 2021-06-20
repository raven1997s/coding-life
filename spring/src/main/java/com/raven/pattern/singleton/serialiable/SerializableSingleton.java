package com.raven.pattern.singleton.serialiable;

import java.io.Serializable;

/**
 * @PackageName: com.raven.pattern.singleton.serialiable
 * @ClassName: SerializableSingleton
 * @Blame: raven
 * @Date: 2021-06-20 9:35
 * @Description: 反序列化导致单例破坏
 */
public class SerializableSingleton implements Serializable {

    // 序列化：
    // 序列化就是把内存中的状态通过转换为字节码的形式
    // 从而转化一个IO流，写入到其他地方(可以是磁盘，网络IO)
    // 内存中的状态给永久的保存下来了

    // 反序列化
    // 将已经持久化的字节码内容，转换为IO流
    // 通过IO流的读取，进而将读取的内容转换为Java对象
    // 在转换过程中会重新创建对象new

    private SerializableSingleton() {
    }

    private static final SerializableSingleton SINGLETON = new SerializableSingleton();

    public static SerializableSingleton getInstance() {
        return SINGLETON;
    }

    /**
     * 阅读源码可知 反序列化的readObject底层原理 只需重写readResolve 即可防止反序列化对象破坏单例
     * readObject ->
     * readObject0 ->
     * readOrdinaryObject ->
     * // 对象是否已实例化，是就new一个对象出来
     * obj = desc.isInstantiable() ? desc.newInstance() : null ->
     * if (obj != null && handles.lookupException(passHandle) == null && desc.hasReadResolveMethod()) ->
     * readResolveMethod = getInheritableMethod(cl, "readResolve", null, Object.class); ->
     * // 不为空且已经实现了ReadResolve方法 就执行该方法 并返回对象
     * Object rep = desc.invokeReadResolve(obj)
     *
     * @return
     */
    private Object readResolve() {
        return SINGLETON;
    }

}
