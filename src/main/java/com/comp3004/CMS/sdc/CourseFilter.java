package com.comp3004.CMS.sdc;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.repository.DeliverableRepository;
import com.comp3004.CMS.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("CourseFilter")
public class CourseFilter implements GradingFilter{

    @Autowired
    private CourseRepository courseRepository;
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

        Set<Session> courses = courseRepository.findSessionsByProgram(program);
        Set<Deliverable> deliverables = deliverableRepository.findDeliverablesByCourseIn(courses);
        Set<StudentGrade> studentGrades = gradeRepository.findStudentGradesByDeliverableIn(deliverables);

        for(StudentGrade sg : studentGrades){
            points.add(new GradePoint(sg.getGrade(), sg.getStudent(), sg.getProfessor(), sg.getSession()));
        }



        return points;
    }
}
