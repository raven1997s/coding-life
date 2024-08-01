package com.raven.dream.job.common.aspect;

import lombok.Data;

/**
 * Description:
 * date: 2023/6/27 18:48
 *
 * @author longjiaocao
 */
@Data
public class AspectInfo {
    private Object[] methodParam;
    private String methodName;
    private Class classType;
}