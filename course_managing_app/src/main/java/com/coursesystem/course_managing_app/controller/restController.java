package com.coursesystem.course_managing_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.coursesystem.course_managing_app.data.Course;
import com.coursesystem.course_managing_app.data.Student;
import com.coursesystem.course_managing_app.services.courseManageService;
import com.coursesystem.course_managing_app.services.studentManageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class restController {
    
    @Autowired
    studentManageService studentService;

    @Autowired
    courseManageService courseService;

    //Student mapping
    //====================

    @GetMapping("/getStudents")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students = studentService.getStudents();
        return students;
    }

    @PostMapping("studentByEmail")
    public Student getStudentByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    @PostMapping("addStudent")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student added";
    }

    @PostMapping("studentsCourses")
    public List<Course> getStudentsCoursesByEmail(@RequestBody Map<String, Object> jsonMapping) {
        String email = jsonMapping.get("email").toString();
        return studentService.getStudentsCoursesByEmail(email);
    }

    @PostMapping("addStudentToCourse") 
    public String addStudentToCourse(@RequestBody Map<String, Object> jsonMapping) {
        String email = jsonMapping.get("email").toString();
        String courseName = jsonMapping.get("course").toString();
        studentService.addStudentToCourse(email, courseName);
        return "Student added to "+ courseName;
    }
    
    @PostMapping("deleteStudent")
    public String deleteStudentByEmail(@RequestParam String email) {
        studentService.deleteStudentByEmail(email);
        return "Student "+email+" removed";
    }

    
    //=====================

    //Course mapping
    //=====================

    @PostMapping("addCourse")
    public String addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return "Course added";
    }

    @GetMapping("/getCourses") 
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    //=====================


}
