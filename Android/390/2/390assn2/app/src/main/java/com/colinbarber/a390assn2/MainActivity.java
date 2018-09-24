package com.colinbarber.a390assn2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*  this activity is what the user sees when the app is opened. It contains a button which
    navigates to the GradeActivity class
 */
public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";
    protected Button viewGradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // when the button is clicked the user will navigate to GradeActivity
        viewGradeButton = findViewById(R.id.gradeButton);
        viewGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GradeActivity.class));
            }
        });
    }


}
