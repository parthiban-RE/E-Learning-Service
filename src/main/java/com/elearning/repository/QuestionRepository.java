package com.elearning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.elearning.model.Question;
import com.elearning.model.Uploaddoc;

public interface QuestionRepository extends CrudRepository<Question, Integer>{
	
	List<Question> findAll();



	Optional<Question> findById(Long questionId);



	//List<Question> findByStudentId(long studentid);



	List<Question> findBystudentId(long studentId);



	List<Question> findBystudentIdAndStatus(Long studentId, String string);



	boolean existsByExamNameAndStudentIdAndQuestionText(String examName, long studentId, String currentQuestionText);



	void deleteByExamNameAndStudentIdAndQuestionText(String examName, long studentId, String currentQuestionText);



	//boolean existsByExamNameAndStudentIdAndQuestionText(String examName, Object studentId, String string);

}
