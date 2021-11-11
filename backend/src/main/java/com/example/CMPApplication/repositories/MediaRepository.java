package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository {

    Integer create(int userId, String name, Long size, String ContentType, byte[] data) throws ETMediaException;

    Media findById(int mediaId,int userId) throws ETMediaException;
}
