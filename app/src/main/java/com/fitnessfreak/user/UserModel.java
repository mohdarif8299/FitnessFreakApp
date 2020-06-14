package com.fitnessfreak.user;

public class UserModel {
    private String email;
    private String username;
    private String password;

    public UserModel(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UserModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
