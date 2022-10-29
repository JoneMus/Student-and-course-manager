package com.coursesystem.course_managing_app.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursesystem.course_managing_app.data.Course;
import com.coursesystem.course_managing_app.data.Student;

@Service
public class studentManageService {

    @Autowired
    courseManageService courseService;

    @Autowired
    fileManageService fileService;

    List<Student> students = new ArrayList<>();
    
    studentManageService() {}

    /*PostConstructilla saadaan haettua opiskelijat, kun ohjelma käynnistyy */

    @PostConstruct
    private void init() {
        try {
        this.students = fileService.getStudentsFromFile();
        }catch (Exception e) {
            System.out.println("Cannot read file");
            System.out.println(e.fillInStackTrace());
        }

    }

    public void addStudent(Student student) {
        students.add (student);
        fileService.addStudentsToFile(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentByEmail(String email) {
        for (Student student : students) {
            if(email.equals(student.getEmail())) {
                System.out.println("Student returned");                
                return student;
            }
        }
        System.out.println("Null returned");  
        return null;
    }

    /*Lisätään opiskelija kurssille käyttäjän antamien tietojen mukaan */
    
    public void addStudentToCourse(String email, String courseName) {
        Student student = getStudentByEmail(email);
        Course course = courseService.getCourseByName(courseName);
        student.addCourse(course);
        fileService.updateStudentCourses(student);
    }


    public List<Course> getStudentsCoursesByEmail(String email) {
        Student student = getStudentByEmail(email);
        return student.getStudentsCourses();
    }

    public void deleteStudentByEmail(String email) {         
        for (int i = (students.size() - 1); i >= 0; i--) {
            Student student = students.get(i);
            if (email.equals(student.getEmail())) {
                fileService.deleteStudentFromFile(student);
                students.remove(student);
            }
        }
    }
}
