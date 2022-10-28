package com.coursesystem.course_managing_app.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.coursesystem.course_managing_app.data.Course;
import com.coursesystem.course_managing_app.data.Student;

@Service
public class fileManageService {


    // Student tiedoston käsittely
    //===============================

    /*Kun uusi opiskelija luodaan se tallennetaan tiedostoon*/

    public void addStudentsToFile(Student student) {   

        try {     
        File students = new File("files\\students.txt");
        FileWriter filewriter = new FileWriter(students, true);
        filewriter.write(String.valueOf(student) + System.lineSeparator());
        filewriter.close();
        }catch (IOException e) {
            System.out.println("An error occured");
        }
    }  

    /*Kun opiskelija lisätään kurssille, viimeksi tallennettu tieto opiskelijasta poistetaan
      ja tiedostoon tallennetaan Student olion uusi versio*/
    
    public void updateStudentCourses(Student student) {
        File students = new File("files\\students.txt");
        File temp = new File("files\\tempFile.txt");

        try {
            Scanner fileScanner = new Scanner(students);
            FileWriter writer = new FileWriter(temp, true);
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();   
                String id = row.substring(row.indexOf("fname:")-2, row.indexOf("fname:")-1);
        
                if (id.equals(String.valueOf(student.getStudentID()))) {
                    System.out.println(id+" "+String.valueOf(student.getStudentID()));
                    writer.write(String.valueOf(student) + System.lineSeparator());                    
                }
                else {
                    writer.write(row + System.lineSeparator());
                }
            }
            fileScanner.close();
            writer.close();
            students.delete();
            temp.renameTo(students);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }catch (IOException e) {
                System.out.println("An error occured");
            }

    }

    /*Sovelluksen käynnistyksessä haetaan tallennetut opiskelijat tiedostosta
    ja luodaan oliot uudestaan */ 

    public List<Student> getStudentsFromFile() {
    File students = new File("files\\students.txt");

        try {
            System.out.println("Reading students file!");
            Scanner fileScanner = new Scanner(students);
            List<Student> studentsList = new ArrayList<>();
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();   
                String fname = row.substring(row.indexOf("fname:")+7,row.indexOf("lname:")-1);
                String lname = row.substring(row.indexOf("lname:")+7,row.indexOf("email:")-1);
                String email = row.substring(row.indexOf("email:")+7,row.indexOf("studentsCourses")-1);
                String courseName;

                Student student = new Student(fname, lname, email);
                studentsList.add(student);

                /*Selvitetään onko opiskelijalle tallennettu kursseja, jotka
                voidaan lisätä uudestaan opiskelijalle */

                try {
                    courseName = row.substring(row.indexOf("studentsCourses[name:")+22, row.indexOf("teacher:")-1);
                    List<String> listOfCourses = new ArrayList<>();
                    System.out.println("Kurssi: "+ courseName);
                    
                    /*Tarkistetaan onko opiskelijalla enemmän kuin yksi kurssi.
                    Jos on kurssien nimet lisätään listaan ja käyttämällä metodia getCoursesByName
                    saadaan palautuksena lista, mistä kurssit voidaan lisätä opiskelijalle. */

                    try {
                        String name;
                        List<Course> courses = new ArrayList<>();
                        int length = row.length();

                        String str = row.substring(row.indexOf("studentsCourses[name:")+16, length);
                        System.out.println(str);

                        int x = str.indexOf(',');
                        char comma = ',';
                        listOfCourses.add(courseName);
                            while (x >= 0) {        
                                if (comma == str.charAt(x)) {             
                                    name = str.substring(x+8,str.indexOf("teacher:", x)-1);
                                    listOfCourses.add(name);
                                    System.out.println(name);                                                    
                                }
                            x = str.indexOf(',',x+1);                                             
                            }
                            courses = getCourseByName(listOfCourses);

                                for (Course course : courses) {
                                    student.addCourse(course);
                                }
                    } catch (Exception e) {
                        System.out.println("Only one course");
                        System.out.println(e.fillInStackTrace());
                        student.addCourse(getCourseByName(courseName));
                    }

                } catch (Exception e) {
                    courseName = null;
                }


            } 
            System.out.println(studentsList);
            fileScanner.close();
            return studentsList;     
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }     
    }

    /*Poistetaan opiskelijan tiedot students tiedostosta */

    public void deleteStudentFromFile(Student student) {
        File students = new File("files\\students.txt");
        File temp = new File("files\\tempFile.txt");

        try {
            Scanner fileScanner = new Scanner(students);
            FileWriter writer = new FileWriter(temp, true);
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();   
                String id = row.substring(row.indexOf("fname:")-2, row.indexOf("fname:")-1);
        
                if (id.equals(String.valueOf(student.getStudentID()))) {
                    System.out.println(id+" "+String.valueOf(student.getStudentID()));
                    System.out.println("Student: "+student+" removed from students.");                    
                }
                else {
                    writer.write(row + System.lineSeparator());
                }
            }
            fileScanner.close();
            writer.close();
            students.delete();
            temp.renameTo(students);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }catch (IOException e) {
                System.out.println("An error occured");
            }

    }

    //===============================

    // Courses tiedoston käsittely
    //===============================

    /*Kun uusi kurssi luodaan se tallennetaan courses tiedotoon */

    public void addCoursesToFile(Course course) {  
        try {     
            File courses = new File("files\\courses.txt");
            FileWriter filewriter = new FileWriter(courses, true);
            filewriter.write(String.valueOf(course) + System.lineSeparator());
            filewriter.close();
        }catch (IOException e) {
            System.out.println("An error occured");
        }
    }

    /*Sovelluksen käynnistyessä haetaan courses tiedostosta kaikki kurssit
     ja luodaan niistö uudet oliot */

    public List<Course> getCoursesFromFile() {
        File courses = new File("files\\courses.txt");
        try {
            System.out.println("Reading courses file!");
            Scanner fileScanner = new Scanner(courses);
            List<Course> coursesList = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String row = fileScanner.nextLine();

                String name = row.substring(row.indexOf("name:")+6, row.indexOf("teacher:")-1);
                String teacher = row.substring(row.indexOf("teacher:")+9, row.indexOf("classRoom:")-1);
                String classRoom = row.substring(row.indexOf("classRoom:")+11);

                Course course = new Course(name,teacher,Integer.parseInt(classRoom));
                coursesList.add(course);
            }
            fileScanner.close();
            return coursesList;
        } catch (Exception e) {
            System.out.println("Cannot get courses from file");
            return null;
        }
    }

    /*Kaksi overloadattua metodia, joilla saadaan courses tiedostoa lukemalla haettua
    kurssit opiskelijalle */

    public Course getCourseByName(String courseName){
        List<Course> courses = getCoursesFromFile();
        for (Course course : courses) {
            if (courseName.equals(course.getName())) {
                return course;
            }
        }
        return null;
    }

    public List<Course> getCourseByName(List<String> courseNames) {
        List<Course> courses = new ArrayList<>();
            for (String courseName : courseNames) {
                courses.add(getCourseByName(courseName));
            }     
        return courses;
    }

    //===============================
}
