package com.raven.pattern.template.jdbc;

import com.google.common.collect.Lists;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.raven.pattern.template.jdbc
 * @ClassName: JdbcTemplate
 * @Blame: raven
 * @Date: 2021-08-03 20:46
 * @Description:
 */
public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 根据sql 查询返回数据列表
     * @param sql 查询sql
     * @param rowMapper 数据RowMapper映射
     * @param values 查询参数列表
     * @return 数据列表
     */
    protected List<?> executeQuery(String sql,RowMapper<?> rowMapper, Object[] values){

        try {
            // 1.获取数据库连接
            Connection conn = this.getConnection();

            // 2.创建语句集
            PreparedStatement pstm =  this.createPrepareStatement(sql,conn);

            // 3.执行语句集
            ResultSet rs = this.executeQuery(pstm, values);

            // 4.处理结果集
           List<?> resultList =  this.parseResultSet(rs,rowMapper);

            // 5.关闭结果集
            this.closeResultSet(rs);

            // 6.关闭语句集
            this.closePreparedStatement(pstm);

            // 7.关闭数据库连接
            this.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection conn) throws Exception{
        // 如果是数据库连接池，就是归还连接数，默认实现关闭
        conn.close();
    }

    private void closePreparedStatement(PreparedStatement pstm) throws Exception{
        pstm.close();
    }

    private void closeResultSet(ResultSet rs) throws Exception{
        rs.close();
    }

    /**
     * 遍历结果集数据，将结果集映射到对象上，处理结果集
     * @param rs 结果集
     * @param rowMapper 映射方法
     * @return 封装好的结果数据
     * @throws Exception
     */
    private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception{
        ArrayList<Object> resultList = Lists.newArrayList();
        int rowNum = 1;
        while (rs.next()){
            resultList.add(rowMapper.mapRow(rs,rowNum++));
        }
        return resultList;
    }

    /**
     * 设置请求参数，执行语句集
     * @param pstm
     * @param values
     * @return
     * @throws Exception
     */
    private ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws Exception{
        // 遍历设置请求参数
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i,values[i]);
        }
        return pstm.executeQuery();
    }

    /**
     * 创建语句集
     * @param sql
     * @param conn
     * @return
     * @throws Exception
     */
    private PreparedStatement createPrepareStatement(String sql, Connection conn) throws Exception{
        return conn.prepareStatement(sql);
    }

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    private Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}
