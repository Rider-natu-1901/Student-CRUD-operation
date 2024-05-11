package com.project.student.request;

import lombok.Data;

@Data
public class CreateStudentRequest {
	private String name;
	private String dob;
	private int yearOfJoin;
	private String email;
	private String address;
	private String schoolName;
	private String city;

}
