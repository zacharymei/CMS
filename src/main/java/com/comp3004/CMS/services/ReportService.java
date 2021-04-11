package com.comp3004.CMS.services;

import com.comp3004.CMS.sdc.*;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReportService {

    @Autowired
    StudentFilter studentFilter;
    @Autowired
    ProfessorFilter professorFilter;
    @Autowired
    CourseFilter courseFilter;

    @Autowired
    BeanFactory beanFactory;

    public Set<GradePoint> filterByProgram(String program, Set<String> target){
        CompoundFilter gradingFilter = new CompoundFilter();


        if(target.contains("student")){
            gradingFilter.add(beanFactory.getBean(studentFilter.getClass()));
        }
        if(target.contains("professor")){
            gradingFilter.add(beanFactory.getBean(professorFilter.getClass()));
        }
        if(target.contains("course")){
            gradingFilter.add(beanFactory.getBean(courseFilter.getClass()));
        }

        Set<GradePoint> output = gradingFilter.inProgram(program);

        return output;
    }


}
