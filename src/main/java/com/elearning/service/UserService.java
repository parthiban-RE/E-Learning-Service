package com.elearning.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.elearning.model.Course;
import com.elearning.model.Joincourses;
import com.elearning.model.Question;
import com.elearning.model.Staff;
import com.elearning.model.Student;
import com.elearning.model.Uploaddoc;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.JoincoursesRepository;
import com.elearning.repository.QuestionRepository;
import com.elearning.repository.StaffRepository;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.UploaddocRepository;


@Service
public class UserService {
	
	
	private static final String DBConnection = null;	
    String emailid ="";
	
    @Autowired
	private StudentRepository stuRepo;
        
    @Autowired
	private StaffRepository staffRepo;
    
    @Autowired
   	private CourseRepository courseRepo;
    
    @Autowired
   	private UploaddocRepository uploadRepo;
	
   

	// STUDENT REGISTRATION //

	public ResponseEntity<String> sturegistration(Student data) {
		boolean isAlreadyExisted = stuRepo.existsByemail(data.getEmail());
		if (isAlreadyExisted) {
			return ResponseEntity.ok("Email Id Is Already Exited");
		}
		stuRepo.save(data);
		return ResponseEntity.ok("Successfully registered");
	}

	
	
	// STAFF REGISTRATION//

	public ResponseEntity<String> stafregi(Staff stf) {
		boolean isAlreadyExisted = staffRepo.existsByemail(stf.getEmail());
		if (isAlreadyExisted) {
			return ResponseEntity.ok("Email Id Is Already Exited");
		}
		staffRepo.save(stf);
		return ResponseEntity.ok("Successfully registered");
	}

	
	 public List<Course>getallcourse(){
			
		 List <Course> list=(List<Course>)courseRepo.findAll();
		
         return list; 
	 }	
	 
	 
	 public List<Course> getByKeyword(String keyword){
		  return courseRepo.findByKeyword(keyword);
		 }
	 
	 
	 
	 public List<Uploaddoc>getdoc(){
			
		 List <Uploaddoc> list=(List<Uploaddoc>)uploadRepo.findAll();
		
         return list; 
	 }	
	 
	 
	 @Autowired
	    private QuestionRepository questionRepository;

	    public List<Question> getAllQuestions() {
	        return questionRepository.findAll();
	    }
	 
	 
	 
	 
}
