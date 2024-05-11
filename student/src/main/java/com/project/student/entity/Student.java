package com.project.student.entity;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student", schema = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "dob")
	private String dob;

	@Column(name = "yearofjoin")
	private int yearOfJoin;

	@Column(name = "email")
	private String email;

	@Column(name = "address", columnDefinition = "TEXT")
	private String address;

	@Column(name = "schoolname")
	private String schoolName;

	@Column(name = "city")
	private String city;

	@Column(name = "time")
	private LocalDateTime time;

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", dob=" + dob + ", yearOfJoin=" + yearOfJoin + ", email="
				+ email + ", address=" + address + ", schoolName=" + schoolName + ", city=" + city + ", time=" + time
				+ "]";
	}

}
