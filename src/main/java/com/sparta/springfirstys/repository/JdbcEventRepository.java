package com.sparta.springfirstys.repository;

import com.sparta.springfirstys.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcEventRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;
    public JdbcEventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Event event) {

    }
}
