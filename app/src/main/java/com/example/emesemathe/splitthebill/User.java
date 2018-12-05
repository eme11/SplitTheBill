package com.example.emesemathe.splitthebill;

public class User {
    private String emailAddress_;
    private String userName_;
    private String phoneNumber_;
    private String idUser_;

    public String getEmailAddress_() {
        return emailAddress_;
    }

    public String getUserName_() {
        return userName_;
    }

    public String getPhoneNumber_() {
        return phoneNumber_;
    }

    public String getIdUser_() {
        return idUser_;
    }

    public void setEmailAddress_(String emailAddress_) {
        this.emailAddress_ = emailAddress_;
    }

    public void setUserName_(String userName_) {

        this.userName_ = userName_;
    }

    public String toString()
    {
        return emailAddress_  + " " + phoneNumber_ + " " + userName_;
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

    public User()
    {
        this.emailAddress_ = "Not set";
        this.userName_ = "Not set";
        this.phoneNumber_ = "Not set";
        this.idUser_ = "Not set";
    }
}
