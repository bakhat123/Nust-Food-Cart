package com.example.layouts.model;

import java.util.Objects;

public class UserModel {
    private String userName;
    private String email;
    private String password;
    private String location;
    private String phoneNumber;

    // Constructor including location and phone number
    public UserModel(String userName, String email, String password, String location) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.location = location;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(userName, userModel.userName) && Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password) && Objects.equals(location, userModel.location) && Objects.equals(phoneNumber, userModel.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password, location, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
