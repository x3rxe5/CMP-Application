package com.example.CMPApplication.models;

public class User {

    // Private variables
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String dob;

    // Constructor
    public User(){}

    public User(int id,String email,String password,String userName){
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public User(String firstName, String lastName, String userName,String email, String password, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public User(int id, String firstName, String userName,String lastName, String email, String password, String dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + userName + '\'' +
                ", dob=" + dob +
                '}';
    }
}
