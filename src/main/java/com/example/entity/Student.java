package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.Transient;

import com.example.request.CreateStudentRequest;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	//@Transient
	//private String fullName;
	
	@JoinColumn(name="address_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Address address;
	
	@OneToMany(mappedBy="student")
	private List<Subject> learningSubjects;
	
	public Student(CreateStudentRequest createStudentRequest) {
		this.firstName= createStudentRequest.getFirstName();
		this.lastName=createStudentRequest.getLastName();
		this.email=createStudentRequest.getEmail();
		//this.fullName =createStudentRequest.getFirstName()+ " " + createStudentRequest.getLastName();
	}
	
}
