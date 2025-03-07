package com.example.inventory.repository;

import com.example.inventory.model.Business;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
    @Repository
    public class BusinessRepository {
        private final JdbcTemplate jdbcTemplate;

        public BusinessRepository(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public HttpStatus addBusiness(Business business) {
            System.out.println(business.toString());
            String sql = "INSERT INTO business (business_id,name,owner_id, location, contact_no) VALUES (?, ?, ?, ?, ?)";
            try {
                jdbcTemplate.update(sql,
                        business.getBusiness_id(),
                        business.getName(),
                        business.getOwner_id(),
                        business.getLocation(),
                        business.getContact_no());
            return HttpStatus.OK;
            } catch (DataAccessException e) {
                return HttpStatus.ALREADY_REPORTED;

            }

        }

        public List<Business> getAllBusiness() {
            String sql = "SELECT * FROM Business";
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Business business = new Business();
                business.setBusiness_id(rs.getInt("business_id"));
                business.setName(rs.getString("name"));
                business.setOwner_id(rs.getString("owner_id"));
                business.setLocation(rs.getString("email"));
                business.setContact_no(rs.getString("password"));
                return business;
            });
        }
    }

