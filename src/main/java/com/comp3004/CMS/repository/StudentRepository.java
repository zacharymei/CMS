package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Student;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

    List<Student> findByLastName(String lastName);
    Student findById(long Id);
}
