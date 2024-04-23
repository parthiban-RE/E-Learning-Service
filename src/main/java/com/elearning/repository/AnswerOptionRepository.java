package com.elearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.AnswerOption;
import com.elearning.model.Uploaddoc;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {

	Optional<AnswerOption> findById(long selectedOptionId);

	AnswerOption findByQuestionIdAndCorrectTrue(Long questionId);

}
