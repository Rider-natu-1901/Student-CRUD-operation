package com.project.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.student.dto.StudentDTO;
import com.project.student.request.CreateStudentRequest;
import com.project.student.request.UpdateStudentRequest;
import com.project.student.response.CreateStudentResponse;
import com.project.student.response.StudentDetailsResponse;
import com.project.student.service.StudentService;
import com.project.student.util.StudentUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/student/s/")
@AllArgsConstructor
@Slf4j
public class StudentController {

	private final StudentService studentService;

	@PostMapping("/student/save-details")
	public ResponseEntity<CreateStudentResponse> saveStudent(@RequestBody CreateStudentRequest request) {
		log.debug("Request received: {}", request);
		StudentDTO studentDTO = StudentUtil.map(request, StudentDTO.class);
		StudentDTO studentDTOResponse = studentService.saveStudentDetails(studentDTO);
		CreateStudentResponse response = StudentUtil.map(studentDTOResponse, CreateStudentResponse.class);
		log.debug("Response generated: {}", response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/student/{emailId}/get-details-by-email")
	public ResponseEntity<StudentDTO> getStudentDetailsByEmail(@PathVariable String emailId) {
		log.debug("Received request to get student details by emailId: {}", emailId);
		StudentDTO studentDTO = studentService.getStudentDetailsByEmailId(emailId);
		log.debug("Returning response for get student details by emailId: {}", studentDTO);
		return ResponseEntity.ok(studentDTO);
	}

	@PutMapping("/student/{emailId}/update-details")
	public ResponseEntity<StudentDTO> updateStudentDetailsByEmailId(@PathVariable String emailId,
			@RequestBody UpdateStudentRequest updateStudentRequest) {
		log.debug("Received request to update student details by emailId: {}, updateStudentRequest: {}", emailId,
				updateStudentRequest);
		StudentDTO studentDTO = studentService.updateStudentDetailsByEmailId(emailId, updateStudentRequest);
		log.debug("Returning response for update student details by emailId: {}", studentDTO);
		return ResponseEntity.ok(studentDTO);
	}

	@DeleteMapping("/student/{emailId}/delete-details")
	public ResponseEntity<StudentDTO> deleteStudentDetailsByEmailId(@PathVariable String emailId) {
	    log.debug("Received request to delete student details by emailId: {}", emailId);
		StudentDTO studentDTO = studentService.deleteStudentDetailsByEmailId(emailId);
	    log.debug("Returning response for delete student details by emailId: {}", studentDTO);
		return ResponseEntity.ok(studentDTO);
	}

	@GetMapping("/student/{city}/get-details")
	public ResponseEntity<StudentDetailsResponse> getStudentDetailsByCity(@PathVariable String city) {
	    log.debug("Received request to get student details by city: {}", city);
		StudentDetailsResponse response = studentService.getStudentDetailsByCity(city);
	    log.debug("Returning response for get student details by city: {}", response);
		return ResponseEntity.ok(response);
	}

}
