package com.colinbarber.a390assn2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;

/*  this activity displays a list view populated by courses and assignment grades. It contains a
    back button and a toggle button to switch between percentage grades and letter grades in the
    action bar.
 */
public class GradeActivity extends AppCompatActivity {
    private String TAG = "GradeActivity";

    ArrayList<Course> courses; // an array of courses we want to display in a list view
    String[] strCourses; // an array to hold each course after converting to a string
    Boolean numToggle = true; // toggles between numbers and letter grades

    ArrayAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grades_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle the clicking of the letter grade toggle button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                numToggle = !numToggle;
                generateStrings();
                populateList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // when the activity is navigated to, new courses are displayed in the list view
    @Override
    protected void onResume() {
        super.onResume();
        listView = findViewById(R.id.list_view);
        generateCourses();
        generateStrings();
        populateList();
    }

    // generates a random number of courses (max 10) to add to the list view
    private void generateCourses() {
        courses = new ArrayList<Course>();

        Random rnd = new Random();
        int courseNo = rnd.nextInt(10)+2;
        strCourses = new String[courseNo];

        for(int j=0; j<courseNo; j++) {
            Course course = Course.generateRandomCourse();
            courses.add(course);
        }
    }

    // fills the array adapter and populates the list view with courses
    private void populateList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strCourses);
        if (listView == null) { Log.e(TAG, "listView is null"); }
        else { listView.setAdapter(adapter); }
    }

    // converts the array of courses into an array of strings to populate the list view
    private void generateStrings() {
        for (int i = 0; i < courses.size(); i++) {
            ArrayList<Assignment> assignments = courses.get(i).getAssignments();
            String converted = courses.get(i).getCourseTitle()+"\n";
            int average = 0;

            if (assignments.size() > 0) {
                for (int j = 0; j < assignments.size(); j++) {
                    converted = converted + "\n" + (assignments.get(j).getAssignmentTitle() + ":  " + convertGrade(assignments.get(j).getAssignmentGrade()));
                    average += assignments.get(j).getAssignmentGrade();
                }
                average = average / assignments.size();
                converted = converted + "\n\n" + "Average: "+convertGrade(average)+"\n";
            }
            strCourses[i] = converted;
        }
    }

    // converts the number grades into letter grades
    private String convertGrade(int numGrade) {

        if (numToggle) {return Integer.toString(numGrade);}
        else {
            if (numGrade >= 90) {return "A+";}
            if (numGrade < 90 && numGrade >= 85) {return "A";}
            if (numGrade < 85 && numGrade >= 80) {return "A-";}
            if (numGrade < 80 && numGrade >= 75) {return "B+";}
            if (numGrade < 75 && numGrade >= 70) {return "B";}
            if (numGrade < 70 && numGrade >= 65) {return "B-";}
            if (numGrade < 65 && numGrade >= 60) {return "C+";}
            if (numGrade < 60 && numGrade >= 55) {return "C";}
            if (numGrade < 55 && numGrade >= 50) {return "C-";}
            if (numGrade < 50 && numGrade >= 40) {return "D";}
            else {return "F";}
        }
    }
}