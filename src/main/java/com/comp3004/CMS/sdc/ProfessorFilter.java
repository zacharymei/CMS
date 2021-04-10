package com.comp3004.CMS.sdc;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.repository.DeliverableRepository;
import com.comp3004.CMS.repository.GradeRepository;
import com.comp3004.CMS.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("ProfessorFilter")
public class ProfessorFilter implements GradingFilter{

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DeliverableRepository deliverableRepository;
    @Autowired
    GradeRepository gradeRepository;


    @Override
    public int sample() {
        return 0;
    }

    @Override
    public Set<GradePoint> inProgram(String program) {

        Set<GradePoint> points = new HashSet<>();

        Set<Professor> professors = professorRepository.findProfessorsByProgram(program);

        // For each professor

        Set<Session> courses = courseRepository.findSessionsByProfessorIn(professors);
        Set<Deliverable> deliverables = deliverableRepository.findDeliverablesByCourseIn(courses);
        Set<StudentGrade> studentGrades = gradeRepository.findStudentGradesByDeliverableIn(deliverables);

        for(StudentGrade sg : studentGrades){
            points.add(new GradePoint(sg.getGrade(), sg.getStudent(), sg.getProfessor(), sg.getSession()));
        }



        return points;
    }
}
