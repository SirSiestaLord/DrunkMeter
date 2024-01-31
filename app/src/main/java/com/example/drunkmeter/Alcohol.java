package com.example.drunkmeter;

import java.util.jar.Attributes;

public class Alcohol {
    public String Name,Brand,Type;
    public int percentageofalcohol,promil;
    public int id;
    public Alcohol(String Name,String Brand,String Type,int percentageofalcohol,int milileter){
        this.Name= Name;
        this.Brand= Brand;
        this.Type= Type;
        this.percentageofalcohol= percentageofalcohol;
        this.promil=milileter;
    }
}
