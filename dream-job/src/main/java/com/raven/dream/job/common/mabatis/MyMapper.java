package com.raven.dream.job.common.mabatis;

/**
 * Description:
 * date: 2023/10/10 15:55
 *
 * @author raven
 */

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionSelectiveMapper;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 * <p>
 * 可以作为通用mapper使用
 */
public interface MyMapper<T>
        extends Mapper<T>, MySqlMapper<T>, IdsMapper<T>, UpdateByConditionSelectiveMapper<T>, ConditionMapper<T> {

    @Select("<script>${sql}</script>")
    void executeSql(@Param("sql") String sql);

}