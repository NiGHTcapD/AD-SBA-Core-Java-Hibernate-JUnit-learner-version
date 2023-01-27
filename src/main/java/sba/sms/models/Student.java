package sba.sms.models;

import java.util.ArrayList;
import java.util.List;

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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
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
	
}
