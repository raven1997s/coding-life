package com.raven.pattern.factory.methodfactory;

import com.raven.pattern.factory.ICourse;

/**
 * 工厂方法模式
 */
public interface IFactory {
    /**
     * 创建课程对象
     * @return
     */
    ICourse createCourse();
}
