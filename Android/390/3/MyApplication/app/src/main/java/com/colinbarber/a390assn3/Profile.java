package com.colinbarber.a390assn3;

/*  Stores a user's name, age and ID
* */
public class Profile {

    private String profileName;
    private int profileAge;
    private int profileID;

    public Profile(String n, int a, int i) {
        profileName = n;
        profileAge = a;
        profileID = i;
    }

    // returns name, age or ID contained in Profile
    public String getProfileName() {return profileName;}
    public int getProfileAge() {return profileAge;}
    public int getProfileID() {return profileID;}
}