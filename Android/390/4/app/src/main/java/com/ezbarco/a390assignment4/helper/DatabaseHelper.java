package com.ezbarco.a390assignment4.helper;

import com.ezbarco.a390assignment4.model.Assignment;
import com.ezbarco.a390assignment4.model.Course;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat Course
    private static final String LOG = DatabaseHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Table Names
    private static final String TABLE_Assignment = "Assignments";
    private static final String TABLE_Course = "Courses";
    private static final String TABLE_Assignment_Course = "Assignment_Courses";

    // Common column names
    private static final String KEY_ID = "id";

    // Assignments Table - column names
    private static final String KEY_Assignment_TITLE = "Assignment_title";
    private static final String KEY_Assignment_GRADE = "Assignment_grade";

    // Courses Table - column names
    private static final String KEY_Course_TITLE = "Course_title";
    private static final String KEY_Course_CODE = "Course_code";

    // NOTE_CourseS Table - column names
    private static final String KEY_Assignment_ID = "Assignment_id";
    private static final String KEY_Course_ID = "Course_id";

    // Table Create Statements
    // Assignment table create statement
    private static final String CREATE_TABLE_Assignment = "CREATE TABLE "
            + TABLE_Assignment + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Assignment_TITLE
            + " TEXT," + KEY_Assignment_GRADE
            + " TEXT" + ")";

    // Course table create statement
    private static final String CREATE_TABLE_Course = "CREATE TABLE " + TABLE_Course
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Course_TITLE + " TEXT,"
            + KEY_Course_CODE + " TEXT" + ")";

    // Assignment_Course table create statement
    private static final String CREATE_TABLE_Assignment_Course = "CREATE TABLE "
            + TABLE_Assignment_Course + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_Assignment_ID + " INTEGER," + KEY_Course_ID + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_Assignment);
        db.execSQL(CREATE_TABLE_Course);
        db.execSQL(CREATE_TABLE_Assignment_Course);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Assignment);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Course);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Assignment_Course);

        // create new tables
        onCreate(db);
    }

    // ------------------------ "Assignments" table methods ----------------//

    /**
     * Creating a Assignment
     */
    public long createAssignment(Assignment Assignment, long[] Course_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Assignment_TITLE, Assignment.getAssignmentName());
        values.put(KEY_Assignment_GRADE, Assignment.getAssignmentGrade());

        // insert row
        long Assignment_id = db.insert(TABLE_Assignment, null, values);

        // insert Course_ids
        for (long Course_id : Course_ids) {
            createAssignmentCourse(Assignment_id, Course_id);
        }

        return Assignment_id;
    }

    /**
     * get single Assignment
     */
    public Assignment getAssignment(long Assignment_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_Assignment + " WHERE "
                + KEY_ID + " = " + Assignment_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Assignment td = new Assignment();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setAssignmentName((c.getString(c.getColumnIndex(KEY_Assignment_TITLE))));
        td.setAssignmentGrade(c.getString(c.getColumnIndex(KEY_Assignment_GRADE)));

        return td;
    }

    /**
     * getting all Assignments
     * */
    public List<Assignment> getAllAssignments() {
        List<Assignment> Assignments = new ArrayList<Assignment>();
        String selectQuery = "SELECT  * FROM " + TABLE_Assignment;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Assignment td = new Assignment();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setAssignmentName((c.getString(c.getColumnIndex(KEY_Assignment_TITLE))));
                td.setAssignmentGrade(c.getString(c.getColumnIndex(KEY_Assignment_GRADE)));

                // adding to Assignment list
                Assignments.add(td);
            } while (c.moveToNext());
        }

        return Assignments;
    }

    /**
     * getting all Assignments under single Course
     * */
    public List<Assignment> getAllAssignmentsByCourse(String Course_name) {
        List<Assignment> Assignments = new ArrayList<Assignment>();

        String selectQuery = "SELECT  * FROM " + TABLE_Assignment + " td, "
                + TABLE_Course + " tg, " + TABLE_Assignment_Course + " tt WHERE tg."
                + KEY_Course_TITLE + " = '" + Course_name + "'" + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_Course_ID + " AND td." + KEY_ID + " = "
                + "tt." + KEY_Assignment_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Assignment td = new Assignment();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setAssignmentName((c.getString(c.getColumnIndex(KEY_Assignment_TITLE))));
                td.setAssignmentGrade(c.getString(c.getColumnIndex(KEY_Assignment_GRADE)));

                // adding to Assignment list
                Assignments.add(td);
            } while (c.moveToNext());
        }

        return Assignments;
    }

    /**
     * getting Assignment count
     */
    public int getAssignmentCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Assignment;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a Assignment
     */
    public int updateAssignment(Assignment Assignment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Assignment_TITLE, Assignment.getAssignmentName());
        values.put(KEY_Assignment_GRADE, Assignment.getAssignmentGrade());

        // updating row
        return db.update(TABLE_Assignment, values, KEY_ID + " = ?",
                new String[] { String.valueOf(Assignment.getId()) });
    }

    /**
     * Deleting a Assignment
     */
    public void deleteAssignment(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Assignment, KEY_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }

    // ------------------------ "Courses" table methods ----------------//

    /**
     * Creating Course
     */
    public long createCourse(Course Course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Course_TITLE, Course.getCoursetitle());
        values.put(KEY_Course_CODE, Course.getCourseCode());

        // insert row
        long Course_id = db.insert(TABLE_Course, null, values);

        return Course_id;
    }

    /**
     * getting all Courses
     * */
    public List<Course> getAllCourses() {
        List<Course> Courses = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + TABLE_Course;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Course t = new Course();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setCoursetitle(c.getString(c.getColumnIndex(KEY_Course_TITLE)));
                t.setCourseCode(c.getString(c.getColumnIndex(KEY_Course_CODE)));

                // adding to Courses list
                Courses.add(t);
            } while (c.moveToNext());
        }
        return Courses;
    }

    /**
     * Updating a Course
     */
    public int updateCourse(Course Course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Course_TITLE, Course.getCoursetitle());
        values.put(KEY_Course_CODE, Course.getCourseCode());

        // updating row
        return db.update(TABLE_Course, values, KEY_ID + " = ?",
                new String[] { String.valueOf(Course.getId()) });
    }

    /**
     * Deleting a Course
     */
    public void deleteCourse(Course Course, boolean should_delete_all_Course_Assignments) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting Course
        // check if Assignments under this Course should also be deleted
        if (should_delete_all_Course_Assignments) {
            // get all Assignments under this Course
            List<Assignment> allCourseAssignments = getAllAssignmentsByCourse(Course.getCoursetitle());

            // delete all Assignments
            for (Assignment Assignment : allCourseAssignments) {
                // delete Assignment
                deleteAssignment(Assignment.getId());
            }
        }

        // now delete the Course
        db.delete(TABLE_Course, KEY_ID + " = ?",
                new String[] { String.valueOf(Course.getId()) });
    }

    // ------------------------ "Assignment_Courses" table methods ----------------//

    /**
     * Creating Assignment_Course
     */
    public long createAssignmentCourse(long Assignment_id, long Course_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Assignment_ID, Assignment_id);
        values.put(KEY_Course_ID, Course_id);

        long id = db.insert(TABLE_Assignment_Course, null, values);

        return id;
    }

    /**
     * Updating a Assignment Course
     */
    public int updateAssignmentCourse(long id, long Course_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Course_ID, Course_id);

        // updating row
        return db.update(TABLE_Assignment, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Deleting a Assignment Course
     */
    public void deleteAssignmentCourse(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Assignment, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}