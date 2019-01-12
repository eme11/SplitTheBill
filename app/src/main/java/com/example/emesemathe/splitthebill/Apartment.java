package com.example.emesemathe.splitthebill;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Apartment implements Serializable
{
    private String idApartment_;
    private String name_;
    private String address_;
    private Double rent_;
    private ArrayList<String> userId_;

    public String getName_()
    {
        return name_;
    }

    public String getIdApartment_()
    {
        return idApartment_;
    }

    public String getAddress_()
    {
        return address_;
    }

    public Double getRent_()
    {
        return rent_;
    }

    public Apartment(String id_, String name_, String address_, String rent_,@NonNull ArrayList<String> userId)
    {
        this.idApartment_ = id_;
        this.name_ = name_;
        this.address_ = address_;
        userId_ = new ArrayList<>();
        for(String user : userId)
        {
            userId_.add(user);
        }
    }

    public Apartment(Apartment a)
    {
        idApartment_ = a.idApartment_;
        name_ = a.name_;
        rent_ = a.rent_;
        address_ = a.address_;
        userId_ = new ArrayList<>();
        setUserId_(a.userId_);
    }

    public Apartment(String id_, String name_, String address_, String rent_, String uid_)
    {
        this.idApartment_ = id_;
        this.name_ = name_;
        this.address_ = address_;
        this.rent_ = Double.parseDouble(rent_);
        userId_ = new ArrayList<>();
        userId_.add(uid_);
    }

    public Apartment()
    {
        idApartment_ = "Not set";
        name_ = "Not set";
        address_ = "Not set";
        rent_ = 0.0;
        userId_ = new ArrayList<>();
    }

    public void setUserId_(ArrayList<String> userId)
    {
        for(String user : userId)
        {
            userId_.add(user);
        }
    }

    public void setIdApartment_(String id)
    {
        idApartment_ = id;
    }

    public void setName_(String name)
    {
        name_ = name;
    }

    public void setAddress_(String address_) {
        this.address_ = address_;
    }

    public void setRent_(Double rent_) {
        this.rent_ = rent_;
    }

    public ArrayList<String> getUserId_()
    {
        return userId_;
    }

    public void addUsers(String userId)
    {
        userId_.add(userId);
    }

    public boolean conatins(String uid)
    {
        return userId_.contains(uid);
    }

    public void addId(String uid)
    {
        userId_.add(uid);
    }

    public void removeId(String uid)
    {
        userId_.remove(uid);
    }

    public void addAllIDs(ArrayList<String> i)
    {
        userId_.addAll(i);
    }

    public String toString()
    {
        return "N:" + name_ + "A:" + address_ + "r:" + rent_ + "uid[0]" + userId_.get(0);
    }
}
