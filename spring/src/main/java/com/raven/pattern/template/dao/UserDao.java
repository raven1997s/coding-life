package com.raven.pattern.template.dao;

import com.raven.pattern.template.entity.User;
import com.raven.pattern.template.jdbc.JdbcTemplate;
import com.raven.pattern.template.jdbc.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * @PackageName: com.raven.pattern.template
 * @ClassName: UserDao
 * @Blame: raven
 * @Date: 2021-08-03 21:38
 * @Description:
 */
@Repository
public class UserDao extends JdbcTemplate {

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll() {
        String sql = "select * from t_user";
        return super.executeQuery(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws Exception {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        }, null);
    }
}
