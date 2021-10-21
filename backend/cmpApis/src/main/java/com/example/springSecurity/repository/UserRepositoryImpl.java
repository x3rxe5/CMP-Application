package com.example.springSecurity.repository;

import com.example.springSecurity.exception.ETAuthException;
import com.example.springSecurity.model.UserModel;
import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final String SQL_CREATE = "INSERT INTO ET_USERS(USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD) " +
            " VALUES (NEXTVAL('ET_USERS_SEQ'),?,?,?,?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM ET_USERS WHERE EMAIL=?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD FROM ET_USERS WHERE USER_ID=?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID,EMAIL,PASSWORD,FIRST_NAME,LAST_NAME FROM ET_USERS WHERE EMAIL=?";

    private RowMapper<UserModel> userRowMapper = ((rs, rowNum) -> {
        return new UserModel(
                rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD")
                );
    });

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws ETAuthException{
        try {
            String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt(12));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setString(3,email);
                ps.setString(4,hashPassword);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e){
            throw new ETAuthException("Invalid details, Failed to create an account");
        }
    }

    // for finding email and Password
    @Override
    public UserModel findByEmailAndPassword(String email, String Password) throws  ETAuthException{
        try{
            UserModel userModel = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL,userRowMapper,email);
            if(!BCrypt.checkpw(Password,userModel.getPassword())){
                throw new ETAuthException("INVALID EMAIL/PASSWORD");
            }
            return userModel;
        }catch(ETAuthException e){
            throw new ETAuthException("INVALID EMAIL/PASSWORD");
        }

    }

    // Email is used or not ?
    @Override
    public Integer getCountByEmail(String email){
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL,Integer.class,email);
    }

    // Find by UserId
    @Override
    public UserModel findById(Integer userId){
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,userRowMapper,userId);
    }

}
