package com.example.CMPApplication.services;

import com.example.CMPApplication.exceptions.ETAuthExceptions;
import com.example.CMPApplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CMPApplication.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws ETAuthExceptions {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password, Date dob) throws ETAuthExceptions {
        Integer userId = userRepository.create(firstName,lastName,email,password,dob);
        return userRepository.findById(userId);
    }
}
