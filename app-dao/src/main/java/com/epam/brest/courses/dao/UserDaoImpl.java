package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by beast on 21.10.14. At 14.19
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("insert into USER (userid, login, name) values (?, ?, ?)",
            user.getUserId(), user.getLogin(), user.getName());
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "select * from USER where userid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {userId}, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        String sql = "select * from USER where login = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{login}, new UserMapper());
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select userid, login, name from USER", new UserMapper());
    }

    @Override
    public void removeUser(Long userId) {
        String sql = "delete from USER where userid = ?";
        jdbcTemplate.update(sql, new Object[]{userId});
    }

    private class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUserId(resultSet.getLong("userid"));
            user.setLogin(resultSet.getString("login"));
            user.setName(resultSet.getString("name"));
            return user;
        }
    }
}
