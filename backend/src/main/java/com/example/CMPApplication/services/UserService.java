package com.example.CMPApplication.services;

import com.example.CMPApplication.exceptions.ETAuthExceptions;
import com.example.CMPApplication.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    User validateUser(String email,String password) throws ETAuthExceptions;
    User registerUser(String firstName,String lastName,String userName,String email,String password,String dob) throws ETAuthExceptions;
    int findUserByIndex(int userId) throws ETAuthExceptions;
    List<User> findAll() throws ETAuthExceptions;
    User userFindById(Integer userId) throws ETAuthExceptions;
}
