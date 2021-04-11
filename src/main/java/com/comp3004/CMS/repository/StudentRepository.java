package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Student;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastName(String lastName);

    @Query("select s.id, s.firstName, s.lastName, s.username, s.password from Student s")
    List<Student> showAll();

    Student findById(long Id);

    Student findStudentByUsername(String username);

    Student findStudentByUsernameEquals(String username);

    Set<Student> findStudentByPasswordEquals(String password);

    int countStudentByUsernameContains(String username);

    //@Query("select s.id from Student s where s.program = :program")
    //Set<Student> findStudents(@Param("program") String program);

    Set<Student> findStudentsByProgram(String program);

}
