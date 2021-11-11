package com.example.CMPApplication.models;

import java.util.Arrays;

public class Media {

    private int mediaId;
    private int userId;
    private String name;
    private Long size;
    private String contentType;
    private byte[] data;

    public Media() {}


    public Media(int mediaId, int userId, String name, Long size, String contentType, byte[] data) {
        this.mediaId = mediaId;
        this.userId = userId;
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.data = data;
    }

    public Media(int userId, String name, Long size, String contentType, byte[] data) {
        this.userId = userId;
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.data = data;
    }

    public Media(String name, Long size, String contentType, byte[] data) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.data = data;
    }


    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Media{" +
                "mediaId=" + mediaId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }


}
