package com.ezbarco.a390assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ezbarco.a390assignment4.helper.DatabaseHelper;
import com.ezbarco.a390assignment4.model.Course;
import com.ezbarco.a390assignment4.utils.MyDividerItemDecoration;
import com.ezbarco.a390assignment4.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private CoursesAdapter mAdapter;
    private List<Course> coursesList = new ArrayList<>();
    private RecyclerView recyclerView;
    public TextView mInputDisplay;
    public String mInput;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        db = new DatabaseHelper(this);

        coursesList.addAll(db.getAllCourses());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog.");
                InsertCourseDialog dialog = new InsertCourseDialog();
                dialog.show(getSupportFragmentManager(), "MyCustomDialog");
            }
        });

        mAdapter = new CoursesAdapter(this, coursesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Intent intent = new Intent(MainActivity.this, AssignmentActivity.class);
                intent.putExtra("title", "code");
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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
