package com.coursesystem.course_managing_app.data;

import java.util.ArrayList;
import java.util.List;

public class Student {
    
    private static int studentIdCounter = 0;
    private int studentID;
    private String fname;
    private String lname;
    private String email;
    private List<Course> studentsCourses;


    public Student(String fname, String lname, String email) {
        this.studentID = studentIdCounter++;
        studentsCourses = new ArrayList<>();
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }


    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public List<Course> getStudentsCourses() {
        return this.studentsCourses;
    }

    public void addCourse(Course course) {
        studentsCourses.add(course);
    }

    @Override
    public String toString() {
        return new StringBuffer("studentID: ").append(this.studentID).append(" fname: ").append(this.fname)
                .append(" lname: ").append(this.lname).append(" email: ").append(email)
                .append(" studentsCourses").append(this.studentsCourses).toString();
    }

}
