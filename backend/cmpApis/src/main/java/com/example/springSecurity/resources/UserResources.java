package com.example.springSecurity.resources;

import com.example.springSecurity.Constants;
import com.example.springSecurity.model.UserModel;
import com.example.springSecurity.services.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserResources {

    @Autowired
    UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Map<String,Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        UserModel userModel = userServices.validateUser(email,password);
        return new ResponseEntity<>(generateJWTToken(userModel), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        UserModel userModel = userServices.registerUser(firstName,lastName,email,password);
        return new ResponseEntity<>(generateJWTToken(userModel), HttpStatus.OK);
    }

    private Map<String,String> generateJWTToken(UserModel userModel){
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
                .claim("userId",userModel.getUserId())
                .claim("firstName",userModel.getFirstName())
                .claim("lastName",userModel.getLastName())
                .claim("email",userModel.getEmail())
                .compact();

        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }
}
