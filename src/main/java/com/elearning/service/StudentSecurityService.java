package com.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearning.model.Student;
import com.elearning.model.StudentDetails;
import com.elearning.repository.StudentRepository;





@Service
public class StudentSecurityService implements UserDetailsService{
	
	@Autowired 
	private StudentRepository stuRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		System.out.println("email....."+email);
		Student stu = stuRepo.findByEmail(email);
		
		if (stu == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}
		
		return new StudentDetails(stu);
	}
	

}