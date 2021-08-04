package com.raven.pattern.adapter.poweradapter;

/**
 * @PackageName: com.raven.pattern.adapter.poweradapter
 * @ClassName: AC220
 * @Blame: raven
 * @Date: 2021-08-04 9:50
 * @Description: 220交流电
 */
public class Ac220 {

    public int outputAc220V(){
        int output = 220;
        System.out.println("输出电流" + output + "V");
        return output;
    }
}
