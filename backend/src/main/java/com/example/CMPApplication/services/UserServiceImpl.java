package com.example.CMPApplication.services;

import com.example.CMPApplication.exceptions.ETAuthExceptions;
import com.example.CMPApplication.models.User;
import com.example.CMPApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws ETAuthExceptions {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null){ email = email.toLowerCase();}

        if(!pattern.matcher(email).matches()){
            throw new ETAuthExceptions("Please provide the valid email");
        }

        return userRepository.findByEmailAndPassword(email, password);

    }

    @Override
    public User registerUser(String firstName, String lastName, String userName,String email, String password, String dob) throws ETAuthExceptions {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null) email = email.toLowerCase();

        if (!pattern.matcher(email).matches()) throw new ETAuthExceptions("Not Valid email address");

        int count = userRepository.getCountByEmail(email);

        if(count > 0) throw new ETAuthExceptions("Email address already registered");

        Integer userId = userRepository.create(firstName,lastName,userName,email,password,dob);
        return userRepository.findById(userId);
    }

    @Override
    public int findUserByIndex(int userid) throws ETAuthExceptions{
        int bool = 0;
        User user = userRepository.findById(userid);
        if(user.getEmail().length() > 1){
            bool = 1;
        }
        return bool;
    }
}
