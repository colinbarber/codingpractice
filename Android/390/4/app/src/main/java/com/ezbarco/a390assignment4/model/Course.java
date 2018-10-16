package com.ezbarco.a390assignment4.model;

public class Course {

    int id;
    String Course_title;
    String Course_code;

    // constructors
    public Course() {

    }

    public Course(String Course_title, String Course_code) {
        this.Course_title = Course_title;
        this.Course_code = Course_code;
    }

    public Course(int id, String Course_title, String Course_code) {
        this.id = id;
        this.Course_title = Course_title;
        this.Course_code = Course_code;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setCoursetitle(String Course_title) {
        this.Course_title = Course_title;
    }

    public void setCourseCode(String Course_code) {
        this.Course_code = Course_code;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getCoursetitle() {
        return this.Course_title;
    }
    public String getCourseCode() {
        return this.Course_code;
    }
}