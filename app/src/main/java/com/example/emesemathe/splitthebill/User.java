package com.example.emesemathe.splitthebill;

public class User {
    private String emailAddress_;
    private String userName_;
    private String phoneNumber_;
    private String idUser_;

    public String getEmailAddress_() {
        return emailAddress_;
    }

    public String getUserName() {
        return userName_;
    }

    public String getPhoneNumber_() {
        return phoneNumber_;
    }

    public String getIdUser_() {
        return idUser_;
    }

    public void setUserName(String userName) {
        this.userName_ = userName;
    }

    public void setPhoneNumber_(String phoneNumber_) {
        this.phoneNumber_ = phoneNumber_;
    }

    public User(String id_, String emailAddress_, String phone_, String name_) {
        this.emailAddress_ = emailAddress_;
        this.userName_ = name_;
        this.phoneNumber_ = phone_;
        this.idUser_ = id_;
    }
}
