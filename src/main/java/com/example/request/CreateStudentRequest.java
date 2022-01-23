package com.example.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {
	@JsonProperty("First_Name")
	@NotBlank(message ="First name is required")
	private String firstName;
	@NotBlank(message="Last name is requiredgy6666")
	private String lastName;
	private String email;
	private String city;
	private String street;
	
	private List<CreateSubjectRequest> subjectLearning;
}
