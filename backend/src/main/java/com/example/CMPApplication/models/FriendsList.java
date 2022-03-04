package com.example.CMPApplication.models;

public class FriendsList {
    private int id;
    private int user_id;
    private int friend_id;
    private int accepted;
    private int blocked_status;

    public FriendsList(){}

    public FriendsList(int id, int user_id, int friend_id, int accepted, int blocked_status) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.accepted = accepted;
        this.blocked_status = blocked_status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getBlocked_status() {
        return blocked_status;
    }

    public void setBlocked_status(int blocked_status) {
        this.blocked_status = blocked_status;
    }


    @Override
    public String toString() {
        return "FriendsList{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", friend_id=" + friend_id +
                ", accepted=" + accepted +
                ", blocked_status=" + blocked_status +
                '}';
    }



}
