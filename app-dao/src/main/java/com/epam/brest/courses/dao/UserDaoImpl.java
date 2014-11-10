package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beast on 21.10.14. At 14.19
 */
public class UserDaoImpl implements UserDao {

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${insert_into_user_path}')).inputStream)}")
    public String addNewUserSql;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select_all_users_path}')).inputStream)}")
    public String selectAllUsersSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select_user_by_id_path}')).inputStream)}")
    public String selectUserByIdSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select_user_by_login_path}')).inputStream)}")
    public String selectUserByLoginSql;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${update_user_path}')).inputStream)}")
    public String updateUserSql;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${delete_user_path}')).inputStream)}")
    public String deleteUserSql;

    public static final String USER_ID = "userid";
    public static final String LOGIN = "login";
    public static final String NAME = "name";

    private static final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addUser(User user) {

        LOGGER.debug("addUser({})", user);

        Assert.notNull(user);
        Assert.isNull(user.getUserId());
        Assert.notNull(user.getLogin(), "User login should be specified.");
        Assert.notNull(user.getName(), "User name should be specified.");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(USER_ID, user.getUserId());
        parameters.addValue(LOGIN, user.getLogin());
        parameters.addValue(NAME, user.getName());

        namedJdbcTemplate.update(addNewUserSql, parameters, keyHolder);

        Long id = keyHolder.getKey().longValue();
        LOGGER.debug("addUser() : id={}", id);

        return id;

    }

    @Override
    public List<User> getUsers() {

        LOGGER.debug("get users()");

        return jdbcTemplate.query(selectAllUsersSql, new UserMapper());
    }

    @Override
    public User getUserById(Long userId) {

        LOGGER.debug("getUserById(userId={})", userId);

        return jdbcTemplate.queryForObject(selectUserByIdSql, new UserMapper(), userId);
    }

    @Override
    public User getUserByLogin(String login) {

        LOGGER.debug("getUserByLogin(login={})", login);

        return jdbcTemplate.queryForObject(selectUserByLoginSql, new String[]{login.toLowerCase()}, new UserMapper());
    }

    @Override
    public void updateUser(User user) {

        LOGGER.debug("updateUser({}).. ", user);


        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put(NAME, user.getName());
        parameters.put(LOGIN, user.getLogin());
        parameters.put(USER_ID, user.getUserId());

        namedJdbcTemplate.update(updateUserSql, parameters);

    }

    @Override
    public void removeUser(Long userId) {

        LOGGER.debug("removeUser(userId={})", userId);

        jdbcTemplate.update(deleteUserSql, userId);

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

    }}