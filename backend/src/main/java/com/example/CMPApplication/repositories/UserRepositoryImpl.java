package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETAuthExceptions;
import com.example.CMPApplication.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {


    // SQL_CREATE STRING
    private static final String SQL_CREATE = "INSERT INTO CA_USERS (USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,DOB) VALUES ( NEXTVAL(CA_USERS_SEQ) , ?, ?, ?, ?, ?)";

    // SQL_FIND_BY ID
    private final static String SQL_FIND_BY_ID = "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,DOB FROM CA_USERS WHERE USER_ID = ?";




    @Autowired
    JdbcTemplate jdbcTemplate;

    public RowMapper<User> userRowMapper = ((rs, rowNum) -> {
       return new User(
               rs.getInt("USER_ID"),
               rs.getString("FIRST_NAME"),
               rs.getString("LAST_NAME"),
               rs.getString("EMAIL"),
               rs.getString("PASSWORD"),
               rs.getDate("DOB")
       );
    });

    @Override
    public Integer create(String firstName, String lastName, String email, String password, Date dob) throws ETAuthExceptions {
        try{
            String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt(12));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setString(3,email);
                ps.setString(4,password);
                ps.setDate(5,dob);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch(Exception e){
            throw new ETAuthExceptions("Error Occur");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ETAuthExceptions {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,userRowMapper,userId);
    }
}
