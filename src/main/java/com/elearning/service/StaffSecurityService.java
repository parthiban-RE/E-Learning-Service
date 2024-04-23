package com.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.elearning.model.Staff;
import com.elearning.model.StaffDetails;
import com.elearning.repository.StaffRepository;




	@Service
	public class StaffSecurityService implements UserDetailsService{
		
		@Autowired 
		private StaffRepository staffRepo;

		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
			System.out.println("email....."+email);
			Staff staff = staffRepo.findByEmail(email);
			
			if (staff == null) {
				throw new UsernameNotFoundException("No user found with the given email");
			}
			
			return new StaffDetails(staff);
		}
		
	}


