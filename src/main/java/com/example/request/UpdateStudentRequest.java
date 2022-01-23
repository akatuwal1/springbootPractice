package com.example.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateStudentRequest {
	
		@NotNull (message = "Student id is required")
		private long id;
		private String firstName;
		private String lastName;
		private String email;

}
