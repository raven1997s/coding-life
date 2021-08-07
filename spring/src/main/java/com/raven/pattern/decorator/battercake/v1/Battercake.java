package com.raven.pattern.decorator.battercake.v1;

import lombok.Data;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v1
 * @ClassName: Battercake
 * @Blame: raven
 * @Date: 2021-08-07 18:49
 * @Description:模拟销售煎饼案例 煎饼实体
 */
@Data
public class Battercake {
    public String getMsg (){
        return "一个煎饼";
    }

    public int getPrice(){
        return 5;
    }
}
