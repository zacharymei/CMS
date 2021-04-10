package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Professor;
import java.util.List;
import java.util.Set;

import com.comp3004.CMS.base.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findById(long Id);

    @Query("select p.id from Professor p where p.program = :program")
    Set<Long> findProfessorIdsByProgram(@Param("program") String program);

    Set<Professor> findProfessorsByProgram(String program);

    Professor findProfessorByUsername(String username);

    int countProfessorsByUsernameContains(String username);


}
