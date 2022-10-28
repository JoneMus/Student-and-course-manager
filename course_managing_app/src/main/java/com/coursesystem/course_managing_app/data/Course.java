package com.coursesystem.course_managing_app.data;
// import java.util.ArrayList;
// import java.util.List;

public class Course {
    
    private String name;
    private String teacher;
    private int classRoom;
    //private List<Student> studentsOnCourse;


    public Course(String name, String teacher, int classRoom) {
        //studentsOnCourse = new ArrayList<>();
        this.name = name;
        this.teacher = teacher;
        this.classRoom = classRoom;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getClassRoom() {
        return this.classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return new StringBuffer("name: ").append(this.name).append(" teacher: ")
        .append(this.teacher).append(" classRoom: ").append(this.classRoom+" ").toString();
    }


    // public List<Student> getStudentsOnCourse() {
    //     return this.studentsOnCourse;
    // }

    // public void addStudentsOnCourse(Student student) {
    //     studentsOnCourse.add(student);
    // }

}
