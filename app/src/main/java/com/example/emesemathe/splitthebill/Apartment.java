package com.example.emesemathe.splitthebill;

import java.util.ArrayList;

public class Apartment
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

    public Apartment(String id_, String name_, String address_, double rent_)
    {
        this.idApartment_ = id_;
        this.name_ = name_;
        this.address_ = address_;
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
}
