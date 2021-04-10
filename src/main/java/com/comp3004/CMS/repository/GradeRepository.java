package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.embeddable.GradeID;
import com.comp3004.CMS.sdc.GradePoint;
import com.comp3004.CMS.sdc.StudentGradeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GradeRepository extends JpaRepository<StudentGrade, GradeID> {

    @Query("select sg from StudentGrade sg where sg.id.studentID IN :ids")
    Set<StudentGrade> findStudentGradesByStudentIdIn(@Param("ids") Set<Long> ids);

    @Query("select sg from StudentGrade sg where sg.id.studentID = :id")
    Set<StudentGrade> findStudentGradesByStudentId(@Param("id") Long id);

    Set<StudentGrade> findStudentGradesByDeliverableIn(Set<Deliverable> deliverable);

}
