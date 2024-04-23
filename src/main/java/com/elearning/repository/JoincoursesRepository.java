package com.elearning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.elearning.model.Joincourses;
import com.elearning.model.Staff;

@Repository
public interface JoincoursesRepository extends CrudRepository<Joincourses, Integer>{

	Joincourses findByCoursenameAndStudentid(String coursename, long studentid);

	//Long findStudentidByCoursenameAndStaffid(String coursename, long staffid, long studentid);

	//Long findStudentidByStaffidAndCoursename(long staffid, String coursename);

	List<Joincourses> findByStaffidAndCoursename(long staffid, String coursename);

	List<Joincourses> findByCoursenameAndStaffid(String examName, long id);

	List<Joincourses> findByStaffid(long id);

	//Long findStudentIdByCourseNameAndStaffId(String coursename, Long staffid);

	

}
