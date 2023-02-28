package com.vikash.kuberio10;

public class User_Info {
    private String username,email,number,search,status,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User_Info(String username, String email, String number, String search , String status, String id) {
        this.username = username;
        this.email = email;
        this.number = number;
        this.search = search;
        this.status = status;
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


}
