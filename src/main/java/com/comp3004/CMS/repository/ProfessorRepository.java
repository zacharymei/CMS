package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Professor;
import java.util.List;

import com.comp3004.CMS.base.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findById(long Id);
}
