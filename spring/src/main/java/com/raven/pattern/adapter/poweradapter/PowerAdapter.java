package com.raven.pattern.adapter.poweradapter;

/**
 * @PackageName: com.raven.pattern.adapter.poweradapter
 * @ClassName: PowerAdapter
 * @Blame: raven
 * @Date: 2021-08-04 9:53
 * @Description: 交流电适配器 实现接口，将Ac220 转为Ac5V
 */
public class PowerAdapter implements Dc5 {

    private Ac220 ac220;

    public PowerAdapter(Ac220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int output5V() {
        int adapterIntput = ac220.outputAc220V();
        int adapterOutput = adapterIntput / 44;
        System.out.println("使用PowerAdapter适配器输入AC" + adapterIntput  + "V，输出AC" + adapterOutput + "V");
        return adapterOutput;
    }
}
