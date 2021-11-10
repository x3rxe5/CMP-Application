package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class MediaRepositoryImpl implements MediaRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(int userId, Double name, String url, Byte[] contentType) throws ETMediaException {
        return null;
    }

    @Override
    public Media findById(int id) throws ETMediaException {
        return null;
    }
}
