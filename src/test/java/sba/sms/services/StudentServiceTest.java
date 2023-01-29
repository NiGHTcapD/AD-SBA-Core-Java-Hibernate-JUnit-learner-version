package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class StudentServiceTest {

    static StudentService studentService;

    @BeforeAll
    static void beforeAll() {
        studentService = new StudentService();
        CommandLine.addData();
    }

    @Test
    void getAllStudents() {

        List<Student> expected = new ArrayList<>(Arrays.asList(
                new Student("reema@gmail.com", "reema brown", "password"),
                new Student("annette@gmail.com", "annette allen", "password"),
                new Student("anthony@gmail.com", "anthony gallegos", "password"),
                new Student("ariadna@gmail.com", "ariadna ramirez", "password"),
                new Student("bolaji@gmail.com", "bolaji saibu", "password")
        ));

        assertThat(studentService.getAllStudents()).hasSameElementsAs(expected);

    }
    
    @Test
    void getStudentCourses(){
    	
    	List<Course> expected = new ArrayList<>(Arrays.asList());
    	assertThat(studentService.getStudentCourses("reema@gmail.com")).hasSameElementsAs(expected);
    	
    	
    }
    
    @Test
    void validateStudent1() {
    	//boolean expected = false;
    	assertThat(!studentService.validateStudent("reema@gmail.com", "narpas sword"));
    }
    
    @Test
    void validateStudent2() {
    	//boolean expected = true;
    	assertThat(studentService.validateStudent("reema@gmail.com", "password"));
    }
    
    @Test
    void validateStudent3() {
    	//boolean expected = false;
    	assertThat(!studentService.validateStudent("aaaaa@aaaaa.aaa", "no matter"));
    }
    
    
    
}