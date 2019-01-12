package com.example.emesemathe.splitthebill;

public class HouseHoldSupply {
    private String uid_;
    private double price_;
    private String name_;

    public HouseHoldSupply( String uid, String name, String price)
    {
        uid_ = uid;
        name_ = name;
        price_ = Double.parseDouble(price);
    }

    public HouseHoldSupply()
    {
        uid_ = "Not set";
        name_ = "Not set";
        price_ = 0.0;
    }

    public String getUid_() {
        return uid_;
    }

    public void setUid_(String uid_) {
        this.uid_ = uid_;
    }

    public double getPrice_()
    {
        return price_;
    }


    public void setPrice_(double price) {
        price_ = price;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String toString()
    {
        return "n: " + name_ + " p: " + price_ + " uid: " + uid_;
    }
}
