package sba.sms.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

public class CourseService implements CourseI  {

	@Override
	public void createCourse(Course course) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			session.persist(course);
			tx.commit();

		} catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public Course getCourseById(int courseId) {
		Transaction tx = null;
		Course c = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		 c = session.get(Course.class, courseId);
		
		} catch(HibernateException ex){
			ex.printStackTrace();
			
			
		}finally {
			session.close();
		}
		
		return c;
	}

	@Override
	public List<Course> getAllCourses() {
		Transaction tx = null;
		List<Course> ccc = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			ccc = session.createQuery("from Course", Course.class).getResultList();
		} catch(HibernateException ex){
			ex.printStackTrace();
			
			
		}finally {
			session.close();
		}
		
		return ccc;
	}

}
