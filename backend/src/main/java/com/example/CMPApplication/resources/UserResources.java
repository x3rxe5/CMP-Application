package com.example.CMPApplication.resources;

import com.example.CMPApplication.Constants;
import com.example.CMPApplication.models.User;
import com.example.CMPApplication.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserResources {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> createUser(@RequestBody Map<String,Object> userMap){

        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        Date dob = (Date) userMap.get("dob");

        User user = userService.registerUser(firstName,lastName,email,password,dob);

        return new ResponseEntity<>(generateToken(user), HttpStatus.CREATED);
    }

    private Map<String,String> generateToken(User user){
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
                .claim("userId",user.getId())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("email",user.getEmail())
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }

}
