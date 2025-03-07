package com.example.inventory.repository;


import com.example.inventory.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HttpStatus addUser(User user) {
        System.out.println(user.toString());
        String sql = "INSERT INTO users (name, surname, email, password, phone_no) VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getPhoneNo());
            return HttpStatus.OK;
        } catch (DataAccessException e) {
            return HttpStatus.ALREADY_REPORTED;

        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNo(rs.getString("phone_no"));
            return user;
        });
    }
}