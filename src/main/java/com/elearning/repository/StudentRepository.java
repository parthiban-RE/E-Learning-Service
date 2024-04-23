package com.elearning.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.Course;
import com.elearning.model.Student;
import com.elearning.model.Uploaddoc;


@Repository
public interface StudentRepository  extends CrudRepository<Student, Integer>{

	Student findByEmail(String email);

	boolean existsByemail(String email);

	Student findByUsername(String username);

	Optional<Student> findById(long studentId);

	//Student findByUsername(String username); 

}
