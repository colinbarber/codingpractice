package com.colinbarber.a390assn3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*  Contains a box for name, age and ID.
*   Allows user to input new information and save.
*   */
public class ProfileActivity extends AppCompatActivity {

    protected EditText nameBox;
    protected EditText ageBox;
    protected EditText idBox;
    protected Button saveButton;
    protected SharedPreferencesHelper sharedPreferences;
    protected boolean editMode;

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.action_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editButton:
                if(editMode) {
                    editMode = false;
                    nameBox.setFocusable(false);
                    ageBox.setFocusable(false);
                    idBox.setFocusable(false);
                    saveButton.setVisibility(View.GONE);
                }
                else{
                    editMode = true;
                    nameBox.setFocusableInTouchMode(true);
                    ageBox.setFocusableInTouchMode(true);
                    idBox.setFocusableInTouchMode(true);
                    saveButton.setVisibility(View.VISIBLE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameBox = findViewById(R.id.nameEditView);
        ageBox = findViewById(R.id.ageEditView);
        idBox = findViewById(R.id.idEditView);
        saveButton = findViewById(R.id.saveButton);
        sharedPreferences = new SharedPreferencesHelper(this);

        editMode = false;
        nameBox.setFocusable(false);
        ageBox.setFocusable(false);
        idBox.setFocusable(false);

        Profile profile = sharedPreferences.getProfile();

        if(profile.getProfileName() != null) nameBox.setText(profile.getProfileName());
        if(profile.getProfileAge() != 0) ageBox.setText(Integer.toString(profile.getProfileAge()));
        if(profile.getProfileID()!= 000000) idBox.setText(Integer.toString(profile.getProfileID()));

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast toast = null;
                String name = nameBox.getText().toString();
                String tempAge = ageBox.getText().toString();
                String tempID = idBox.getText().toString();

                if(isEmpty(name)||isEmpty(tempAge)||isEmpty(tempID)){
                    toast = Toast.makeText(getApplicationContext(),"Don't leave empty fields", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                int age = Integer.parseInt(tempAge);
                int id = Integer.parseInt(tempID);

                if (age < 18 || age > 99) {
                    toast = Toast.makeText(getApplicationContext(), "Age not in range 18-99" , Toast.LENGTH_LONG);
                }
                else {
                    sharedPreferences.updateProfile(new Profile(name,age,id));
                    toast = Toast.makeText(getApplicationContext(),"Saved!", Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });
    }

    protected boolean isEmpty(String x) {
        if (x.trim().length() > 0)
            return false;
        return true;
    }
}