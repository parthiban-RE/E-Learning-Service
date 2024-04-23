package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elearning.model.Course;


@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findById(long id);

	//List<Course> findByCoursenameContaining(String keyword);

	@Query(value = "select * from Course s where s.keywords like %:keyword% or s.coursename like %:keyword%", nativeQuery = true)
	 List<Course> findByKeyword(@Param("keyword") String keyword);

}
