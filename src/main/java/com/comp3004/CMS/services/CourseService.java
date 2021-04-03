package com.comp3004.CMS.services;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll(){
        return (List<Course>) courseRepository.findAll();
    }

    public Course findById(long id){
        return courseRepository.findById(id);
    }

    public boolean addCourse(String program, String number){
        Course c = new Course();
        c.setProgram(program);
        c.setNumber(number);
        courseRepository.save(c);
        return true;
    }

    public boolean register(long id, Student s){
        Course c = courseRepository.findById(id);
        c.register(s);
        courseRepository.save(c);
        return true;
    }

}
