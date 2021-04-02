package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Course;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{

//     @Query("SELECT c FROM Course c INNER JOIN c.registered WHERE  = id")
//     List<Long> findByStudents_id(@Param("id") long Id);

    Course findById(long id);

}
