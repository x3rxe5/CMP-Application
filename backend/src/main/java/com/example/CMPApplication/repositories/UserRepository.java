package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETAuthExceptions;
import com.example.CMPApplication.models.User;

import java.sql.Date;

public interface UserRepository {

    // for creating user
    Integer create(String firstName, String lastName,String userName, String email, String password, String dob) throws ETAuthExceptions;

    // finding by email id and password
    User findByEmailAndPassword(String email,String password) throws ETAuthExceptions;

    // whether email being used or not
    Integer getCountByEmail(String email);

    // for finding user by id;
    User findById(Integer userId);
}
