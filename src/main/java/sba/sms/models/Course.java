package sba.sms.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Course {
	@Id
	@Column
	int id;
	@NonNull
	@Column(length = 50)
	String name;
	@NonNull
	@Exclude
	@Column(length = 50)
	String instructor;
	
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses",
	cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@Column
	List<Student> students = new ArrayList<>();

	public List<Student> getStudent() {
		// TODO Auto-generated method stub
		return students;
	}
}
