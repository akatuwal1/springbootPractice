package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Address;
import com.example.entity.Student;
import java.lang.String;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	List<Student> findByFirstName(String firstName);
		
	//Student findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Student> findByFirstNameOrLastName(String firstName, String lastName );
	
	List<Student> findByFirstNameIn(List<String> firstNames);
	
	List<Student> findByFirstNameContains(String firstName);
	
	List<Student> findByFirstNameStartsWith(String firstName);
	
	@Query("From Student Where firstName= :firstName and lastName= :lastName")
	Student getByFirstNameAndLastName( String firstName, String lastName);
	
	@Modifying
	@Transactional
	@Query("Update Student set firstName= :firstName where id= :id")
	Integer updateFirstName(Long id, String firstName);
	
	@Modifying
	@Transactional
	@Query("Delete From Student where firstName = :firstName")
	Integer deleteByFirstName(String firstName); 
		
	List<Student> findByAddressCity(String city);
	
	@Query("From Student where address.city= :city")
	List<Student> getByAddressCity(String city);
	
}
