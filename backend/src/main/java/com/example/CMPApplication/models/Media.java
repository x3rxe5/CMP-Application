package com.example.CMPApplication.models;

public class Media {

    private int id;
    private String name;
    private Long size;
    private String url;
    private String contentType;

    public Media() {}

    public Media(int id,String name, Long size, String url, String contentType) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.url = url;
        this.contentType = contentType;
    }

    public Media(String name, Long size, String url, String contentType) {
        this.name = name;
        this.size = size;
        this.url = url;
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", url='" + url + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

}
