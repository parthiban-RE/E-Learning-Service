package com.elearning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elearning.model.Testmarks;

public  interface TestmarksRepository  extends CrudRepository<Testmarks, Integer>{

	List<Testmarks> findByStudentId(long studentId);

}
