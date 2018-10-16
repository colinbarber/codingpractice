package com.ezbarco.a390assignment4;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AssignmentActivity extends AppCompatActivity implements InsertCourseDialog.OnInputListener  {
    private static final String TAG = "AssignmentActivity";

    public TextView mInputDisplay;
    public String mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog.");
                InsertCourseDialog dialog = new InsertCourseDialog();
                dialog.show(getSupportFragmentManager(), "MyCustomDialog");
            }
        });
    }

    private void setInputToTextView(){
        mInputDisplay.setText(mInput);
    }
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: got the input: " + input);
        mInput = input;
        setInputToTextView();
    }
}
