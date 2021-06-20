package com.raven.pattern.singleton.register;

/**
 * @PackageName: com.raven.pattern.singleton.register
 * @ClassName: EnumSingleton
 * @Blame: raven
 * @Date: 2021-06-20 10:35
 * @Description: 通过枚举实现注册式单例
 */
public enum  EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
