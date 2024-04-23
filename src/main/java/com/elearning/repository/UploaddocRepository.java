package com.elearning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.elearning.model.Uploaddoc;

@Repository
public interface UploaddocRepository  extends CrudRepository<Uploaddoc, Integer>{

	List<Uploaddoc> findByStudentid(long id);

	Optional<Uploaddoc> findById(Long fileid);

}
