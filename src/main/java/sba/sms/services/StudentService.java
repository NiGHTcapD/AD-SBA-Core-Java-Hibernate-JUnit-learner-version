package sba.sms.services;

import java.util.List;

import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;

public class StudentService implements StudentI {

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Student getStudentByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateStudent(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerStudentToCourse(String email, int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Course> getStudentCourses(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
