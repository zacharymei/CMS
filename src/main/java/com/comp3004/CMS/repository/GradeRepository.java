package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.embeddable.GradeID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<StudentGrade, GradeID> {




}
