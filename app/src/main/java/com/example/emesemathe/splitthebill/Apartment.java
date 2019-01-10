package com.example.emesemathe.splitthebill;

import android.widget.ProgressBar;

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

    public Apartment(String id_, String name_, String address_, String rent_)
    {
        this.idApartment_ = id_;
        this.name_ = name_;
        this.address_ = address_;
        this.rent_ = Double.parseDouble(rent_);
        userId_ = new ArrayList<>();
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
}
