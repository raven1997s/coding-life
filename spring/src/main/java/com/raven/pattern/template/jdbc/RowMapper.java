package com.raven.pattern.template.jdbc;

import java.sql.ResultSet;

/**
 * @PackageName: com.raven.pattern.template.jdbc
 * @ClassName: RowMapper
 * @Blame: raven
 * @Date: 2021-08-03 20:44
 * @Description: ORM映射定制化的接口
 * 定义一个模板规范，所有的结果映射都需要执行这个接口
 */
public interface RowMapper<T> {
    /**
     * 规范结果映射
     * @param rs
     * @param rowNum 数据的行数
     * @return 映射返回的数据
     * @throws Exception
     */
    T mapRow(ResultSet rs, int rowNum) throws Exception;
}
