package com.example.inventory.repository;

import com.example.inventory.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public HttpStatus addUser(User user) {
        String sql = "INSERT INTO users (name, surname, email, password, phone_no) VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getPhoneNo());
            return HttpStatus.CREATED;
        } catch (DataAccessException e) {
            return HttpStatus.CONFLICT;
        }
    }

    // READ ALL
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

    // READ BY EMAIL
    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNo(rs.getString("phone_no"));
                return user;
            }, email));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    // UPDATE
    public HttpStatus updateUser(User user) {
        String sql = "UPDATE users SET name = ?, surname = ?, email = ?, password = ?, phone_no = ? WHERE user_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getPhoneNo(), user.getUserId());
            return rowsAffected > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        } catch (DataAccessException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    // DELETE
    public HttpStatus deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, userId);
            return rowsAffected > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        } catch (DataAccessException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
