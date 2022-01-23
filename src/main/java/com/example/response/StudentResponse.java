package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Student;
import com.example.entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;
//import lombok.ToString;

@Getter
@Setter

//@ToString
//@EqualsAndHashCode*/

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class StudentResponse {
	@JsonIgnore
	
	private long id;
	@JsonProperty("First_Name")
	private String firstName;
	private String lastName;
	private String email;
	//private String fullName;
	private String city;
	private String street;
	
	private List<SubjectResponse> learningSubjects;
	
	public StudentResponse (Student student) {
		this.id =student.getId();
		this.firstName=student.getFirstName();
		this.lastName=student.getLastName();
		this.email=student.getEmail();
		//this.fullName=student.getFirstName() + " " + student.getLastName();
		this.city=student.getAddress().getCity();
		this.street=student.getAddress().getStreet();
		
		if(student.getLearningSubjects() !=null) {
			learningSubjects = new ArrayList<SubjectResponse>();
			for(Subject subject: student.getLearningSubjects()) {
				learningSubjects.add(new SubjectResponse(subject));
			}
			
		}
		
	}
	 
	

}
