package com.example.CMPApplication.repositories;

import com.example.CMPApplication.exceptions.ETFriendListException;
import com.example.CMPApplication.models.FriendsList;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsListRepository {
    Integer create(int id, int user_id, int friend_id, int accepted, int blocked_status) throws ETFriendListException;

    FriendsList findById(Integer id) throws ETFriendListException;
}
