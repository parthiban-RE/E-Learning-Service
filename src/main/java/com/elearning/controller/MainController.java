package com.elearning.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elearning.model.AnswerOption;
import com.elearning.model.Course;
import com.elearning.model.Joincourses;
import com.elearning.model.Question;
import com.elearning.model.Staff;
import com.elearning.model.Student;
import com.elearning.model.Testmarks;
import com.elearning.model.Uploaddoc;
import com.elearning.repository.AnswerOptionRepository;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.JoincoursesRepository;

import com.elearning.repository.QuestionRepository;
import com.elearning.repository.StaffRepository;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.TestmarksRepository;
import com.elearning.repository.UploaddocRepository;
import com.elearning.service.UserService;


@Controller
@CrossOrigin(origins = "*")

public class MainController {

	
	@Value("${BASE_DIRECTORY_PATH}")
    private String baseDirectoryPath;
	
	
	@Autowired
	private UserService userServe;
	
	
	@Autowired
	private StudentRepository stuRepo;
	
	@Autowired
	private StaffRepository staffRepo;
			
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private UploaddocRepository uploadRepo;
	
	@Autowired
	private JoincoursesRepository joinRepo;
	
	@Autowired
	private AnswerOptionRepository answerRepo;
	
	@Autowired
	private TestmarksRepository testRepo;
	
	@GetMapping("/")
	public String viewHomePage() {

		return "/index";
	}

	
	@GetMapping("/stuindex")
	public String userin() {

		return "/stuindex";
	}
	
	
	@GetMapping("/staffindex")
	public String clientinde() {

		return "/staffindex";
	}
	
			
	@GetMapping("/signup")
	public String viewSignupPage() {
		return "signup";
	}
	
	@GetMapping("/staffsignup")
	public String client() {
		return "staffsignup";
	}
	
	@GetMapping("/student/login")
	public String viewLoginPage() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";

	}
	
	@RequestMapping("/stafflogin")
	public String cllogin() {
		return "stafflogin";

	}
		
	@GetMapping("/staff/stafflogin")
	public String clientlogin() {
		return "stafflogin";
	}
	
	@GetMapping("/contact")
	public String con() {
		return "contact";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signuppost(HttpServletRequest request, Student data)throws NoSuchAlgorithmException {

		userServe.sturegistration(data);		
		System.out.println("User Email" + data.getEmail());  
		data.setPassword(passwordEncoder.encode(data.getPassword()));
		stuRepo.save(data);
		return "redirect:/login";
		
	}
		
	
	@RequestMapping(value = "/staffsignup", method = RequestMethod.POST)
	public String staffreg(HttpServletRequest request ,Staff staf)throws NoSuchAlgorithmException {
		userServe.stafregi(staf);
		System.out.println("User Email" + staf.getEmail());  
		staf.setPassword(passwordEncoder.encode(staf.getPassword()));			
     	staffRepo.save(staf);
		return "redirect:/stafflogin";
		
	}
	
	
	
	@GetMapping("/staff/apply")
	public String apply(Principal principal,Model model) {
		Staff stf = staffRepo.findByEmail(principal.getName());
		System.out.println("username....." + staffRepo.findByUsername(stf.getUsername()).getId());
		
		model.addAttribute("staffUsername", stf.getUsername());
		System.out.println("staffUsername....."+stf.getUsername());
		model.addAttribute("staffemail", stf.getEmail());
		model.addAttribute("staffcontact", stf.getContactno());
		model.addAttribute("staffgender", stf.getGender());
		
		return "applycourse";
	}
	
	
	@RequestMapping(value = "/staff/applycoures", method = RequestMethod.POST)
	public String course(HttpServletRequest request ,Course cou,Principal principal)throws NoSuchAlgorithmException {
			 	
		Staff stf = staffRepo.findByEmail(principal.getName());
		System.out.println("username....." + staffRepo.findByUsername(stf.getUsername()).getId());
		stf.getId();
		String courseName = cou.getCoursename();
        String description = cou.getDescription(); 
        
        Course course = new Course();
        course.setCoursename(courseName);
        course.setDescription(description);
        
        System.out.println("courseName....."+courseName);
        System.out.println("description....."+description);
         
        course.setStaff(stf);
        System.out.println("stf......"+stf);
        
        course.setStaffUsername(stf.getUsername());
        //course.setContactNumber(staff.getContactNumber());
        //course.setEmail(staff.getEmail());
     	courseRepo.save(course);
     	
		return "redirect:/staffindex";
		
	}
		
	
	
	@RequestMapping(path = { "/courseslist" })

	public String search(Principal principal, Model model) {

		List<Course> list = userServe.getallcourse();

		model.addAttribute("list", list);

		return "courselist";
	}

	
	
	
	@GetMapping("/staff/upload")
	public String upload() {
		return "uploaddoc";
	}
	
	
	@RequestMapping(value = "/staff/uploaddoc", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, Uploaddoc data, @RequestParam("file") MultipartFile file, Model model, Principal principal) {
	    Staff stf = staffRepo.findByEmail(principal.getName());
	    System.out.println("username....." + staffRepo.findByUsername(stf.getUsername()).getId());

	    try {
	        // Ensure base directory exists
	        File baseDirectory = new File(baseDirectoryPath);
	        if (!baseDirectory.exists()) {
	            baseDirectory.mkdirs();
	        }

	        String filePath = baseDirectoryPath;
	        File destDirectory = new File(filePath);
	        System.out.println(destDirectory);
	        // Create directory if it doesn't exist
	        if (!destDirectory.exists()) {
	            destDirectory.mkdirs();
	        }

	        // Save the file
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        File dest = new File(destDirectory, fileName);
	        file.transferTo(dest);
	        System.out.println("file...." + file.getOriginalFilename());

	        data.setFileName(fileName);
	        data.setStaff(stf);

	        long staffid = stf.getId();
	        String coursename = data.getCoursename();

	        // Find matching data in the joincourse table
	        List<Joincourses> joinCourses = joinRepo.findByStaffidAndCoursename(staffid, coursename);
	        if (!joinCourses.isEmpty()) {
	            // Iterate through the list of joinCourses
	            for (Joincourses joinCourse : joinCourses) {
	                // Set student id from joinCourse
	                data.setStudentid(joinCourse.getStudentid());

	                // Save data
	                uploadRepo.save(data);
	            }

	            model.addAttribute("message", "File successfully uploaded and saved at: " + filePath);
	            return "redirect:/staffindex";
	        } else {
	            // Handle case where no matching data is found in joincourse table
	            model.addAttribute("error", "No matching data found in joincourse table.");
	            return "errorPage";
	        }

	    } catch (IOException e) {
	        model.addAttribute("error", "An error occurred while processing the file: " + e.getMessage());
	        return "errorPage";
	    }
	}


	
	
	
	@RequestMapping(value = "/student/joincourse", method = RequestMethod.POST, params = "accept")
	public String accept(@RequestParam("id") long id, HttpServletRequest request, Model model, Principal principal) {

		Student stu = stuRepo.findByEmail(principal.getName());
		System.out.println("username....." + stuRepo.findByUsername(stu.getUsername()).getId());
		
		System.out.println("inside accept..........");
		Course crs = courseRepo.findById(id);
		System.out.println("id........." + crs.getId());
		
		Joincourses join = new Joincourses();         
        join.setCoursename(crs.getCoursename());
        join.setStudentid(stu.getId());
        join.setStaffUsername(crs.getStaffUsername());
        join.setDescription(crs.getDescription());
        join.setStaffid(crs.getStaff().getId());
        join.setStudentname(stu.getUsername());
        join.setStatus("Joined");
        
        joinRepo.save(join);
		return "courselist";

	}
			
	
	@RequestMapping(path = {"/search"})
	 public String home(Course cspt, Model model,String keyword) {
		 if(keyword!=null) {
	   List<Course> list = userServe.getByKeyword(keyword);
	   model.addAttribute("list", list);
	   
	  }else{
		  
	  List<Course> list = userServe.getallcourse();
	  model.addAttribute("list", list);}	      
	  return "courselist";
	 }
	
	
	
	@RequestMapping(path = { "/student/doclist" })

	public String black(Principal principal, Model model) {

		Student user = stuRepo.findByEmail(principal.getName());
		System.out.println("username....." + stuRepo.findByUsername(user.getUsername()).getId());

		List<Uploaddoc> doc = uploadRepo.findByStudentid(stuRepo.findByUsername(user.getUsername()).getId());
		for (Uploaddoc list : doc) {

			System.out.println("Student ID....." + list.getStudentid());
		}

		model.addAttribute("doc", doc);

		return "Doclist";
	}
	
	
	
	//download
	
	@PostMapping("/student/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("id") Long fileid) {
        
        Uploaddoc uploadDoc = uploadRepo.findById(fileid).orElse(null);
        if (uploadDoc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        
        String fileName = uploadDoc.getFileName();
        Path filePath = Paths.get(baseDirectoryPath, fileName);
        System.out.println("fileId: " + fileid);
        System.out.println("filePath: " + filePath);
        System.out.println("fileName: " + fileName);

        Resource resource;
        try {
        	
            // Load the file resource
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Ensure the resource exists and is readable
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Set content disposition header for file download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        // Return the file as ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
	
	


    @Autowired
    private QuestionRepository questionRepository;

    
   //join course list//
    
    @RequestMapping(path = { "/staff/joinlist" })
    public String joinlist(Principal principal, Model model) {
        Staff stf = staffRepo.findByEmail(principal.getName());
        
        
        List<Staff> staffList = staffRepo.findByusername(stf.getUsername());
        if (staffList.isEmpty()) {
          
        } else {
            
            Staff foundStaff = staffList.get(0);
            
            
            System.out.println("Found staff ID: " + foundStaff.getId());
            
            
            List<Joincourses> doc = joinRepo.findByStaffid(foundStaff.getId());
            for (Joincourses list : doc) {
                System.out.println("Staff ID: " + list.getStaffid());
            }

            
            model.addAttribute("doc", doc);
        }

        return "joinlist";
    }
    
    

    @GetMapping("/staff/addquestion")
    public String showAddQuestionForm() {
        return "addquestion";
    }

    @PostMapping("/staff/addquestion")
    @Transactional
    public String addQuestions(@RequestParam("examName") String examName,
                               @RequestParam("questionText") List<String> questionText,
                               @RequestParam("answers") List<String> answers,
                               @RequestParam("correct") List<Integer> correctIndices,
                               Principal principal) {

        Staff staff = staffRepo.findByEmail(principal.getName());
        long staffId = staff.getId();

        List<Joincourses> joinCourses = joinRepo.findByCoursenameAndStaffid(examName, staffId);

        for (Joincourses joinCourse : joinCourses) {
            long studentId = joinCourse.getStudentid();

            for (int i = 0; i < questionText.size(); i++) {
                String currentQuestionText = questionText.get(i);
                
                // Check if the question already exists for the current exam, student, and question text
                if (questionRepository.existsByExamNameAndStudentIdAndQuestionText(examName, studentId, currentQuestionText)) {
                    // If the question already exists, delete it
                    questionRepository.deleteByExamNameAndStudentIdAndQuestionText(examName, studentId, currentQuestionText);
                }

                Question question = new Question();
                question.setExamName(examName);
                question.setStudentId(studentId);
                question.setStaff(staff);
                question.setQuestionText(currentQuestionText);

                List<AnswerOption> answerOptions = new ArrayList<>();
                for (int j = i * 4; j < (i + 1) * 4; j++) {
                    AnswerOption answerOption = new AnswerOption();
                    answerOption.setAnswerText(answers.get(j));
                    answerOption.setCorrect(j - i * 4 == correctIndices.get(i));
                    answerOption.setQuestion(question);
                    answerOptions.add(answerOption);
                }

                question.setAnswerOptions(answerOptions);
                questionRepository.save(question);
            }
        }

        return "addquestion";
    }


    
    
    @RequestMapping(path = {"/student/questions"})
    public String stutest(Principal principal, Model model) {
        Student user = stuRepo.findByEmail(principal.getName());
        Long studentId = stuRepo.findByUsername(user.getUsername()).getId();

        List<Question> pendingQuestions = questionRepository.findBystudentIdAndStatus(studentId, "Pending");
        model.addAttribute("questions", pendingQuestions);

        return "questions";
    }
	
    

    @PostMapping("/student/submitanswers")
    public String submitAnswers(@RequestParam("questionIds") List<Long> questionIds,
                                @RequestParam("selectedOptions") List<Integer> selectedOptions,
                                Principal principal, Model model) {
System.out.println("questionIds,.,.,,."+questionIds);
System.out.println("selectedOptions.,.,.,"+selectedOptions);
        if (questionIds.size() != selectedOptions.size()) {
        	System.out.println("questionIds===="+questionIds);
        	System.out.println("selectedOptions===="+selectedOptions);
            // Handle the case where the number of questions and selected options don't match
            // You can redirect the user to an error page or handle it in any other appropriate way
           
        }

        Student student = stuRepo.findByEmail(principal.getName());
        long studentId = student.getId();
        int totalMarks = 0; // Initialize total marks

        List<Boolean> isCorrectList = new ArrayList<>(); // List to store whether each question was answered correctly

        for (int i = 0; i < questionIds.size(); i++) {
            long questionId = questionIds.get(i);
            int selectedOptionIndex = selectedOptions.get(i);

            // Fetch the question from the database
            Optional<Question> optionalQuestion = questionRepository.findById(questionId);
            if (optionalQuestion.isPresent()) {
                Question question = optionalQuestion.get();
                question.setStatus("completed");
                questionRepository.save(question);
                // Fetch the selected answer option from the database
                List<AnswerOption> answerOptions = question.getAnswerOptions();
                if (selectedOptionIndex >= 0 && selectedOptionIndex < answerOptions.size()) {
                    AnswerOption selectedAnswer = answerOptions.get(selectedOptionIndex);

                    // Check if the selected answer is correct
                    boolean isCorrect = selectedAnswer.isCorrect();
                    System.out.println("Question ID: " + questionId + ", isCorrect: " + isCorrect);

                    // Save the attended question and answer to a new table (Assuming you have a AttendQuestionAnswer table)
                    Testmarks testmarks = new Testmarks();
                    testmarks.setStudentId(studentId);
                    testmarks.setQuestion(question);
                    testmarks.setAnswerOption(selectedAnswer);
                    testmarks.setCorrect(isCorrect);
                    testRepo.save(testmarks);

                    // Accumulate marks for correct answers
                    if (isCorrect) {
                        totalMarks++;
                        System.out.println("totalMarks...." + totalMarks);
                    }
                    
                    // Add isCorrect value to the list
                    isCorrectList.add(isCorrect);
                }
            }
        }

        model.addAttribute("isCorrectList", isCorrectList);

        return "redirect:/student/result";
    }


    
    
    @GetMapping("/student/result")
    public String viewResult(Model model, Principal principal) {
        // Fetch the student
        Student student = stuRepo.findByEmail(principal.getName());
        long studentId = student.getId();

        // Fetch the test marks for the student
        List<Testmarks> testMarks = testRepo.findByStudentId(studentId);

        // Add test marks to the model
        model.addAttribute("testMarks", testMarks);

        return "result"; // Assuming your result HTML page is named "result.html"
    }
    
    
    
   
}


