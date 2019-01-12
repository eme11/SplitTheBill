package com.example.emesemathe.splitthebill;

public class HouseHoldSupply {
    private String uid_;
    private String aid_;
    private double price_;
    private String name_;

    public HouseHoldSupply(String aid, String uid, String name, double price)
    {
        aid_ = aid;
        uid_ = uid;
        name_ = name;
        price_ = price;
    }

    public String getUid_() {
        return uid_;
    }

    public void setUid_(String uid_) {
        this.uid_ = uid_;
    }

    public String getAid_() {
        return aid_;
    }

    public void setAid_(String aid_) {
        this.aid_ = aid_;
    }

    public double getPrice_() {
        return price_;
    }

    public void setPrice_(double price_) {
        this.price_ = price_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }
}
