package com.colinbarber.a390assn3;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesHelper {

    private SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context){
        prefs = context.getSharedPreferences("ProfilePreference",Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public Profile getProfile(){
        String name = prefs.getString("profileName",null);
        int age = prefs.getInt("profileAge", 0);
        int id = prefs.getInt("profileID",000000);
        return new Profile(name, age, id);
    }

    public void updateProfile(Profile profile){
        editor.putString("profileName", profile.getProfileName());
        editor.putInt("profileAge",profile.getProfileAge());
        editor.putInt("profileID",profile.getProfileID());
        editor.apply();
    }

}
