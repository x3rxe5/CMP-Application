package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository {

    Integer create(int userId,Double name,String url,Byte[] contentType) throws ETMediaException;

    Media findById(int id) throws ETMediaException;
}
