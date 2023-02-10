package com.vikash.kuberio10.SQLite_Database;

public class Data {
    private int id;
    private String firstname,lastname;
    private String phoneNumber;
    private String emailid;

    public Data(String firstname, String phoneNumber,String lastname,String emailid) {
        this.firstname = firstname;
        this.phoneNumber = phoneNumber;
        this.lastname=lastname;
        this.emailid=emailid;

    }

    public Data(int id, String firstname, String lastname,String emailid) {
        this.id = id;
        this.firstname = firstname;
        this.lastname=lastname;
        this.emailid=emailid;
        this.phoneNumber = phoneNumber;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }

    public String getEmailid(){
        return emailid;
    }
    public void setEmailid(String emailid){
        this.emailid=emailid;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
