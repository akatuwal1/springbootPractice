package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
//import com.example.entity.Student1;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
//import com.example.request.CreateStudent1Request;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	
	public Student CreateStudent(CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		address.setCity(createStudentRequest.getCity());
		address.setStreet(createStudentRequest.getStreet());
		
		address=addressRepository.save(address);
		
		student.setAddress(address);
		student =studentRepository.save(student);
		
		List<Subject> subjectsList =new ArrayList<Subject>();
		
		if (createStudentRequest.getSubjectLearning() !=null) {
			for(CreateSubjectRequest createSubjectRequest
					:createStudentRequest.getSubjectLearning()) {
					Subject subject = new Subject();
					subject.setSubjectName(createSubjectRequest.getSubjectName());
					subject.setMarksObtained(createSubjectRequest.getMarksObtained());
					subject.setStudent(student);
					
					subjectsList.add(subject);
			}
			subjectRepository.saveAll(subjectsList);
		}
		student.setLearningSubjects(subjectsList);
		
		return student;
	}
	
	public Student updateStudnet(UpdateStudentRequest updateStudentRequest) {
		Student student= studentRepository.findById(updateStudentRequest.getId()).get();
		if (updateStudentRequest.getFirstName()!=null &&
				!updateStudentRequest.getFirstName().isEmpty()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		student = studentRepository.save(student);
		return student;
		
		
	}
	public String deleteStudent(long id) {
		studentRepository.deleteById(id);
		return "Student has been deleted";
	}
	public List<Student> getByFirstName(String firstName){
		return studentRepository.findByFirstName(firstName);
	}
	public Student getByFirstNameAndLastName(String firstName, String lastName) {
		
		//return studentRepository.findByFirstNameAndLastName(firstName, lastName);
		return studentRepository.getByFirstNameAndLastName(firstName, lastName);
	}
	public List<Student> getByFirstNameOrLastName(String firstName, String lastName){
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	}
	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest){
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}
	public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize){
		PageRequest pageable = PageRequest.of(pageNo-1, pageSize);
		return studentRepository.findAll(pageable).getContent();
	}
	public List<Student> getAllStudentsWithSorting(){
		Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
		return studentRepository.findAll(sort);
		
	}
	public List<Student> like(String firstName){
		return studentRepository.findByFirstNameContains(firstName);
		
	}
	public List<Student> startsWith(String firstName){
		return studentRepository.findByFirstNameStartsWith(firstName);
	}
	public Integer updateStudentWithJpql(Long id, String firstName) {
		return studentRepository.updateFirstName(id, firstName);
	}
	public Integer deleteStudent(String firstName) {
		return studentRepository.deleteByFirstName(firstName);
	}
	public List<Student> getByCity(String city){
		return studentRepository.findByAddressCity(city);
	}
}
