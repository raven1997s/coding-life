package com.raven.pattern.adapter.poweradapter;

/**
 * @PackageName: com.raven.pattern.adapter.poweradapter
 * @ClassName: PowerAdapterTest
 * @Blame: raven
 * @Date: 2021-08-04 9:56
 * @Description:
 */
public class PowerAdapterTest {
    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter(new Ac220());
        int i = powerAdapter.output5V();
        System.out.println(i);
    }
}
