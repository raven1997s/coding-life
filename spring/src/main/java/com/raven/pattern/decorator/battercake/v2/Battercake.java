package com.raven.pattern.decorator.battercake.v2;

import lombok.Data;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v1
 * @ClassName: Battercake
 * @Blame: raven
 * @Date: 2021-08-07 18:49
 * @Description:模拟销售煎饼案例 煎饼实体
 */
@Data
public abstract class Battercake {
    /**
     * 购买煎饼具体信息
     * @return
     */
    public abstract String getMsg();

    /**
     * 煎饼价格
     * @return
     */
    public abstract int getPrice();
}
