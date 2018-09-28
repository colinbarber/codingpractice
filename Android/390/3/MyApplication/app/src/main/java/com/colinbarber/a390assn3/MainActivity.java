package com.colinbarber.a390assn3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*  The main activity contains a button displaying the name of the profile.
*   Clicking the button takes the user to the ProfileActivity.
*   */
public class MainActivity extends AppCompatActivity {
    protected Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.profileButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProfileActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE);
        String name =  prefs.getString("profileName",null);
        if (name == null)
            gotoProfileActivity();
        else
            button.setText(name);
    }

    protected void gotoProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
