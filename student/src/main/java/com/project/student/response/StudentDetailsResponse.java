package com.project.student.response;

import java.util.ArrayList;
import java.util.List;

import com.project.student.dto.StudentDTO;

import lombok.Data;

@Data
public class StudentDetailsResponse {

	private List<StudentDTO> response = new ArrayList<>();

}
