package com.project.student.response;

import lombok.Data;

@Data
public class CreateStudentResponse {

	private String name;
	private String dob;
	private int yearOfJoin;
	private String address;
	private String schoolName;
	private String city;

}
