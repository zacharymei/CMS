package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Course;
import java.util.List;
import java.util.Set;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Session, Long> {

//     @Query("SELECT c FROM Course c INNER JOIN c.registered WHERE  = id")
//     List<Long> findByStudents_id(@Param("id") long Id);

    Session findById(long id);

    Set<Session> findSessionsByProgram(String program);

    Set<Session> findSessionsByProfessor(Professor p);

    Set<Session> findSessionsByProfessorIn (Set<Professor> professors);

}
