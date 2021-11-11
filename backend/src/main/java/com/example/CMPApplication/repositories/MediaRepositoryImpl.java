package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
public class MediaRepositoryImpl implements MediaRepository{

    // create the media
    private static final String SQL_CREATE = "INSERT INTO ca_media ( MEDIA_ID, USER_ID, NAME, MEDIA_SIZE, CONTENTTYPE, DATA) VALUES ( NEXTVAL('CA_MEDIA_SEQ'), ?, ?, ?, ?, ?)";

    // find by id
    private static final String SQL_FIND_BY_ID = "SELECT MEDIA_ID, USER_ID, NAME, MEDIA_SIZE, CONTENTTYPE, DATA FROM ca_media WHERE MEDIA_ID = ? AND USER_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(int userId, String name,Long size,String ContentType,byte[] data) throws ETMediaException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,userId);
                ps.setString(2,name);
                ps.setLong(3,size);
                ps.setString(4,ContentType);
                ps.setBytes(5,data);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("MEDIA_ID");
        }catch (Exception e){
            System.out.print("\n\nError occured while uploading media and its log -> "+e);
            throw new ETMediaException("Problem while uploading media");
        }
    }

    @Override
    public Media findById(int mediaId,int userId) throws ETMediaException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,mediaRowMapper,new Object[]{mediaId,userId});
    }

    public RowMapper<Media> mediaRowMapper = ((rs, rowNum) -> {
        return new Media(
                rs.getInt("MEDIA_ID"),
                rs.getInt("USER_ID"),
                rs.getString("NAME"),
                rs.getLong("MEDIA_SIZE"),
                rs.getString("CONTENTTYPE"),
                rs.getBytes("DATA")
        );
    });

}
