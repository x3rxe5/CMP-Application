package com.example.springSecurity.repository;

import com.example.springSecurity.exception.ETAuthException;
import com.example.springSecurity.model.UserModel;

public interface UserRepository {

    // for create the new user
    Integer create(String firstName,String lastName,String email,String password) throws ETAuthException;

    // for finding email and Password
    UserModel findByEmailAndPassword(String email,String Password) throws  ETAuthException;

    // Email is used or not ?
    Integer getCountByEmail(String email);

    // Find by UserId
    UserModel findById(Integer userId);

}