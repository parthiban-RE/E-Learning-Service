package com.elearning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.Course;
import com.elearning.model.Staff;
import com.elearning.model.Uploaddoc;

@Repository
public interface StaffRepository  extends CrudRepository<Staff, Integer>{

	

	Staff findByEmail(String email);

	boolean existsByemail(String email);

	Staff findByUsername(String username);

	Optional<Staff> findById(long staffId);
	
	List <Staff>findByusername(String username);
}
