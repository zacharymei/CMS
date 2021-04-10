package com.comp3004.CMS.sdc;


import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.GradeRepository;
import com.comp3004.CMS.repository.StudentRepository;
import com.comp3004.CMS.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("StudentFilter")
public class StudentFilter implements GradingFilter{

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public int sample(){
        return 1;
    }

    @Override
    public Set<GradePoint> inProgram(String program){
        Set<GradePoint> points = new HashSet<>();

        Set<Long> student_ids = studentRepository.findStudentIdByProgramEquals(program);
        Set<StudentGrade> studentGrades = gradeRepository.findStudentGradesByStudentIdIn(student_ids);

        for(StudentGrade sg : studentGrades){
            points.add(new GradePoint(sg.getGrade(), sg.getStudent(), sg.getProfessor(), sg.getSession()));
        }

        return points;
    }

}
