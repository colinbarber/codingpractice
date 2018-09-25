package com.colinbarber.a390assn3;

public class Profile {

    private String profileName;
    private int profileAge;
    private int profileID;

    public Profile(String name, int age, int id){
        profileName = name;
        profileAge = age;
        profileID = id;
    }

    public void setProfileName(String name){profileName=name;}
    public void setProfileAge(int age){profileAge=age;}
    public void setProfileID(int id){profileID=id;}
    public String getProfileName(){return profileName;}
    public int getProfileAge(){return profileAge;}
    public int getProfileID(){return profileID;}
}