package com.comp3004.CMS.services;

import com.comp3004.CMS.base.Course;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionService{

    @Autowired
    private CourseRepository courseRepository;

    public List<Session> findAll(){
        return (List<Session>) courseRepository.findAll();
    }

    public Session findById(long id){
        return courseRepository.findById(id);
    }

    public void save(Session c){
        courseRepository.save(c);
    }

    public boolean addCourse(String program, String number, String session, String term, String time){
        Session c = new Session();
        c.setProgram(program);
        c.setNumber(number);
        c.setSession(session);
        c.setTerm(term);
        c.setTime(time);
        courseRepository.save(c);
        return true;
    }

    public boolean register(long id, Student s){
        Session c = courseRepository.findById(id);
        c.register(s);
        courseRepository.save(c);
        return true;
    }

    public Set<Student> courseRegistered(long id){
        Session c = findById(id);
        return c.getRegistered();
    }



}
