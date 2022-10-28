package com.coursesystem.course_managing_app.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.coursesystem.course_managing_app.data.Course;

@Service
public class courseManageService {

    @Autowired
    fileManageService fileService;

    private List<Course> courses = new ArrayList<>();
    
    public courseManageService() {}

    /*PostConstructilla saadaan haettua kurssit, kun ohjelma käynnistyy */

    @PostConstruct
    private void init() {
        try {
            this.courses = fileService.getCoursesFromFile();
        } catch (Exception e) {
            System.out.println("Cannot read courses file");
            System.out.println(e.fillInStackTrace());
        }
    }
    
    public void addCourse(Course course) {
        courses.add(course);
        fileService.addCoursesToFile(course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public Course getCourseByName(String courseName) {
        for (Course course : courses) {
            if (courseName.equals(course.getName())) {
                return course;
            }
        }
        return null;
    }
    

}
