package com.project.student.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.student.dto.StudentDTO;
import com.project.student.entity.Student;
import com.project.student.repositroy.StudentRepository;
import com.project.student.request.UpdateStudentRequest;
import com.project.student.response.StudentDetailsResponse;
import com.project.student.util.StudentUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentDTO saveStudentDetails(StudentDTO studentDTO) {
		log.debug("Request received: {}", studentDTO);
		Optional<Student> student = studentRepository.findByEmail(studentDTO.getEmail());
		if (student.isPresent()) {
			throw new RuntimeException("Student with email " + studentDTO.getEmail() + " already exists");
		}
		Student saveStudent = StudentUtil.map(studentDTO, Student.class);

		saveStudent.setTime(LocalDateTime.now());
		log.info("Before saving: {}", saveStudent);
		saveStudent = studentRepository.save(saveStudent);
		log.info("After saving: {}", saveStudent);

		StudentDTO response = StudentUtil.map(saveStudent, StudentDTO.class);
		log.debug("Response generated: {}", response);
		return response;

	}

	public StudentDTO getStudentDetailsByEmailId(String emailId) {
	    log.debug("Getting student details for email: {}", emailId);
		StudentDTO studentDTO = new StudentDTO();
		Optional<Student> student = studentRepository.findByEmail(emailId);
		if (student.isPresent()) {
			studentDTO = StudentUtil.map(student.get(), StudentDTO.class);
	        log.debug("Retrieved student details: {}", studentDTO);
			return studentDTO;
		}
	    log.debug("No student found for email: {}", emailId);
		return studentDTO;

	}

	public StudentDTO updateStudentDetailsByEmailId(String emailId, UpdateStudentRequest updateStudentRequest) {
		log.debug("Updating student details for email: {}, updateStudentRequest: {}", emailId, updateStudentRequest);

		Optional<Student> student = studentRepository.findByEmail(emailId);
		if (student.isEmpty()) {
			throw new RuntimeException("Student with email " + emailId + " its not exists");
		}

		Student updateStudent = student.get();

		StudentUtil.map(updateStudentRequest, updateStudent);
		log.debug("Updated student after mapping: {}", updateStudent);

		updateStudent = studentRepository.save(updateStudent);
		log.debug("Saved updated student: {}", updateStudent);

		StudentDTO studentDTO = StudentUtil.map(updateStudent, StudentDTO.class);
		log.debug("Updated student DTO: {}", studentDTO);
		return studentDTO;

	}

	public StudentDTO deleteStudentDetailsByEmailId(String emailId) {
		log.debug("Deleting student details for email: {}", emailId);

		Optional<Student> student = studentRepository.findByEmail(emailId);
		if (student.isEmpty()) {
			throw new RuntimeException("Student with email " + emailId + " its not exists");
		}

		Student deleteStudent = student.get();

		studentRepository.delete(deleteStudent);
		log.debug("Deleted student: {}", deleteStudent);

		StudentDTO studentDTO = StudentUtil.map(deleteStudent, StudentDTO.class);
		log.debug("Deleted student DTO: {}", studentDTO);
		return studentDTO;

	}

	public StudentDetailsResponse getStudentDetailsByCity(String city) {
		log.debug("Fetching student details for city: {}", city);
		List<Student> listOfStudents = studentRepository.findByCity(city);
		log.info("Found {} students for city {}", listOfStudents.size(), city);
		List<StudentDTO> studentDTOs = listOfStudents.stream()
				.map(student -> StudentUtil.map(student, StudentDTO.class)).collect(Collectors.toList());
		log.info("Mapped student DTOs: {}", studentDTOs);
		StudentDetailsResponse studentDetailsResponse = new StudentDetailsResponse();
		studentDetailsResponse.setResponse(studentDTOs);
		log.debug("Returning student details response: {}", studentDetailsResponse);
		return studentDetailsResponse;
	}

}
