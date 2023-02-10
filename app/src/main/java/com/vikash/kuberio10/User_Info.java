package com.vikash.kuberio10;

public class User_Info {
    private String username,email,number,password;

    public User_Info(String username, String email, String number, String password) {
        this.username = username;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public User_Info() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
