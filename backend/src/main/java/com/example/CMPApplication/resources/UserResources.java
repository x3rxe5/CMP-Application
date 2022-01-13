package com.example.CMPApplication.resources;

import com.example.CMPApplication.Constants;
import com.example.CMPApplication.models.User;
import com.example.CMPApplication.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserResources {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(HttpServletResponse response, @RequestBody Map<String, Object> userMap){

        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email,password);

        // for setting up cookie
        Map<String,String> cookieMap = generateToken(user);

        // adding cookie to the response
        response.addCookie(generateCookie(cookieMap.get("token")));


        // return the status
        return new ResponseEntity<>(cookieMap, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> createUser(HttpServletResponse response,@RequestBody Map<String,Object> userMap){

        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String dob = (String) userMap.get("dob");
        String userName = (String) userMap.get("username");
        userName = generateUUIDToken(userName);

        User user = userService.registerUser(firstName,lastName,userName,email,password,dob);

        // for setting up cookie
        Map<String,String> cookieMap = generateToken(user);
//        ResponseCookie resCookie = generateCookie(cookieMap.get("token"));

        // adding cookie to the response
        response.addCookie(generateCookie(cookieMap.get("token")));


        return new ResponseEntity<>(cookieMap, HttpStatus.CREATED);
    }

    @GetMapping("/read-cookie")
    public ResponseEntity<Map<String,String>> readTheCookie(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        try{
            Cookie[] cookies = request.getCookies();
            String cookieValue = "";
            System.out.println("First step Approached from here"+cookies);
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println(cookie);
                    if (cookie.getName().equals("token")) {
                        System.out.println("Cookie existed and value is "+cookie.getValue());
                        cookieValue = cookie.getValue();
                    }
                }
            }
            map.put("fetchedTokenId","existed "+cookieValue);
        }catch(Exception e){
            map.put("error",e.toString());
            System.out.print("error -> "+e);
        }
        return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }

    @GetMapping("/testCookie")
    public ResponseEntity<Map<String,String>> testCookie(@CookieValue(name = "token") String token){
        Map<String,String> map = new HashMap<>();
        System.out.println("token from outside -> "+token);
        if(token!=null){
            System.out.println(token);
        }

        return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }

    private Map<String,String> generateToken(User user){
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
                .claim("userId",user.getId())
                .claim("username",user.getUserName())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("email",user.getEmail())
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }

    private String generateUUIDToken(String username){

        UUID uuid = UUID.randomUUID();
        String numericUUID = Long.toString(uuid.getMostSignificantBits())
                + Long.toString(uuid.getLeastSignificantBits());


        return username + "#" + numericUUID.substring(3,7);
    }

    private Cookie generateCookie(String token){
//        Cookie cookie = new Cookie("token", token);
//        cookie.setMaxAge(3 * 24 * 60 * 60); // expires in 3 days
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        return cookie;
        Cookie cookie = new Cookie("token",token);

        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);

        // optional properties
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        // add cookie to response
        return cookie;
    }

}
