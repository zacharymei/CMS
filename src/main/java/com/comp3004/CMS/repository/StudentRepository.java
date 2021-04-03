package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Student;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastName(String lastName);
    Student findById(long Id);
}
