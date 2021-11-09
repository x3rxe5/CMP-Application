package com.example.CMPApplication.services;

import com.example.CMPApplication.exceptions.ETAuthExceptions;
import com.example.CMPApplication.models.User;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public interface UserService {
    User validateUser(String email,String password) throws ETAuthExceptions;
    User registerUser(String firstName,String lastName,String email,String password,String dob) throws ETAuthExceptions;
}
