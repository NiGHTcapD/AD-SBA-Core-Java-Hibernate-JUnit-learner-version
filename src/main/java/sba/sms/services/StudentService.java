package sba.sms.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sba.sms.utils.HibernateUtil;

import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;

public class StudentService implements StudentI {

	@Override
	public List<Student> getAllStudents() {
		Transaction tx = null;
		List<Student> sss = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sss = session.createQuery("from student", Student.class).getResultList();
		} catch(HibernateException ex){
			ex.printStackTrace();
			
			
		}finally {
			session.close();
		}
		
		return sss;
	}

	@Override
	public void createStudent(Student student) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			session.persist(student);
			tx.commit();

		} catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public Student getStudentByEmail(String email) {
		Transaction tx = null;
		Student s = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		 s = session.get(Student.class, email);
		
		} catch(HibernateException ex){
			ex.printStackTrace();
			
			
		}finally {
			session.close();
		}
		
		return s;
	}

	@Override
	public boolean validateStudent(String email, String password) {
		Transaction tx = null;
		boolean match = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Student s = session.get(Student.class, email);
			if (s == null) {return match;}
			tx = session.beginTransaction();
			String p = s.getPassword();
			if (password.equals(p)) {match = true;}
		} catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		return match;
	}

	@Override
	public void registerStudentToCourse(String email, int courseId) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Student s = session.get(Student.class, email);
			Course c = session.get(Course.class, courseId);
			tx = session.beginTransaction();
			if (!s.getCourse().contains(c)) {
				s.addCourse(c);
				session.merge(s);
				tx.commit();
			}
		} catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public List<Course> getStudentCourses(String email) {
		Transaction tx = null;
		List<Course> result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			Query<Student> q = session.createQuery("FROM Student WHERE email = :emailId", Student.class);
					q.setParameter("emailId", email);
			Student s = q.getSingleResult();
            result = s.getCourse();
			tx.commit();

		} catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
		return result;
	}

}
