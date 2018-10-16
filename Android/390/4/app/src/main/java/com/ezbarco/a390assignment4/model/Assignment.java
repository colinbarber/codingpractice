package com.ezbarco.a390assignment4.model;

public class Assignment {

    int id;
    String Assignment_name;
    String Assignment_grade;

    // constructors
    public Assignment() {

    }

    public Assignment(String Assignment_name, String Assignment_grade) {
        this.Assignment_name = Assignment_name;
        this.Assignment_grade = Assignment_grade;
    }

    public Assignment(int id, String Assignment_name, String Assignment_grade) {
        this.id = id;
        this.Assignment_name = Assignment_name;
        this.Assignment_grade = Assignment_grade;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setAssignmentName(String Assignment_name) {
        this.Assignment_name = Assignment_name;
    }

    public void setAssignmentGrade(String Assignment_grade) {
        this.Assignment_grade = Assignment_grade;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getAssignmentName() {
        return this.Assignment_name;
    }
    public String getAssignmentGrade() {
        return this.Assignment_grade;
    }
}