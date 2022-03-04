package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETFriendListException;
import com.example.CMPApplication.models.FriendsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FriendsListRepositoryImpl implements FriendsListRepository{
    //Create String
    private final static String SQL_CREATE = "INSERT INTO ca_friends_list(FRD_ID,USER_ID,FRIEND_ID,ACCEPTED,BLOCKED_STATUS) VALUES (NEXTVAL('ca_friends_list_seq'),?,?,?,?)";
    // Find by ID but only With -> FRD_ID
    private final static String SQL_FIND_BY_ID = "SELECT (FRD_ID,USER_ID,FRIEND_ID,ACCEPTED,BLOCKED_STATUS) FROM ca_friends_list WHERE FRD_ID=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(int id, int user_id, int friend_id, int accepted, int blocked_status) throws ETFriendListException {
        return null;
    }

    @Override
    public FriendsList findById(Integer id) throws ETFriendListException {
        return null;
    }
}
