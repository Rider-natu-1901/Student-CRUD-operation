package com.project.student.request;

import lombok.Data;

@Data
public class UpdateStudentRequest {

	private String name;
	private String dob;
	private int yearOfJoin;
	private String address;
	private String schoolName;
	private String city;

}
