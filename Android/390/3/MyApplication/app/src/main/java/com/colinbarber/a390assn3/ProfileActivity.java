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

    public class ProfileActivity extends AppCompatActivity {

        protected Button saveButton;
        protected EditText nameEditText;
        protected EditText ageEditText;
        protected EditText idEditText;
        protected SharedPreferencesHelper sharedPreferences;
        protected boolean editMode;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            setContentView(R.layout.activity_profile);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            nameEditText = findViewById(R.id.nameEditView);
            ageEditText = findViewById(R.id.ageEditView);
            idEditText = findViewById(R.id.idEditView);
            saveButton = findViewById(R.id.saveButton);
            sharedPreferences = new SharedPreferencesHelper(this);

            editMode = false;
            nameEditText.setFocusable(false);
            ageEditText.setFocusable(false);
            idEditText.setFocusable(false);

            Profile profile = sharedPreferences.getProfile();

            if(profile.getProfileName() != null) nameEditText.setText(profile.getProfileName());
            if(profile.getProfileAge() != 0) ageEditText.setText(Integer.toString(profile.getProfileAge()));
            if(profile.getProfileID()!= 000000) idEditText.setText(Integer.toString(profile.getProfileID()));

            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Toast toast = null;


                    String name = nameEditText.getText().toString();
                    String tempAge = ageEditText.getText().toString();
                    String tempID = idEditText.getText().toString();

                    if(isEmpty(name)||isEmpty(tempAge)||isEmpty(tempID)){
                        toast = Toast.makeText(getApplicationContext(),"Don't leave empty fields", Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }

                    int age = Integer.parseInt(tempAge);
                    int id = Integer.parseInt(tempID);

                    if(age < 18 || age > 99)
                        toast = Toast.makeText(getApplicationContext(), "Age not in range 18-99" , Toast.LENGTH_LONG);
                    else {
                        sharedPreferences.updateProfile(new Profile(name,age,id));
                        toast = Toast.makeText(getApplicationContext(),"Saved!", Toast.LENGTH_LONG);
                    }
                    toast.show();

                }
            });
        }

        //Checking if the EditText is empty
        protected boolean isEmpty(String x){
            if (x.trim().length() > 0)
                return false;
            return true;
        }

        //inflate the menu
        @Override
        public boolean onCreateOptionsMenu( Menu menu )
        {
            getMenuInflater().inflate( R.menu.action_menu, menu );
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case R.id.editButton:
                    if(editMode) {
                        editMode = false;
                        nameEditText.setFocusable(false);
                        ageEditText.setFocusable(false);
                        idEditText.setFocusable(false);
                        saveButton.setVisibility(View.GONE);
                    }
                    else{
                        editMode = true;
                        nameEditText.setFocusableInTouchMode(true);
                        ageEditText.setFocusableInTouchMode(true);
                        idEditText.setFocusableInTouchMode(true);
                        saveButton.setVisibility(View.VISIBLE);
                    }
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    }