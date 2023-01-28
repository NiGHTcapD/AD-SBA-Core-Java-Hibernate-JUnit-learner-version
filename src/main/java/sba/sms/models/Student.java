package sba.sms.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "student")
@Entity
public class Student {
	@Id
	@Column(length = 50)
	String email;
	@NonNull
	@Column(length = 50)
	String name;
	@NonNull
	@Exclude
	@Column(length = 50)
	String password;
	
	
	@Column
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	
	@JoinTable(name="student_courses",
	joinColumns = @JoinColumn(name="student_email"),
	inverseJoinColumns = @JoinColumn(name="courses_id"))
	List<Course> courses = new ArrayList<Course>();


	public Student(String newEmail, String newName, String newPassword) {
		email=newEmail;
		name=newName;
		password=newPassword;
	}


	public void addCourse(Course c) {
		courses.add(c);
		c.getStudent().add(this);
	}


	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}


	public List<Course> getCourse() {
		return courses;
	}


	@Override
	public int hashCode() {
		return Objects.hash(courses, email, name, password);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	
}
