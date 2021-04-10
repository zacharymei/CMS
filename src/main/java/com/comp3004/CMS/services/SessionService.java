package com.comp3004.CMS.services;

import com.comp3004.CMS.base.Course;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SessionService implements Observer{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    AdminService adminService;

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

        if(!adminService.loggedIn()){
            return false;
        }

        Session c = new Session();
        c.setProgram(program);
        c.setNumber(number);
        c.setSession(session);
        c.setTerm(term);
        c.setTime(time);

        c.addObserver(this);
        courseRepository.save(c);
        return true;
    }


    public Set<Student> courseRegistered(long id){
        Session c = findById(id);
        return c.getRegistered();
    }


    @Override
    public void update(Observable o, Object arg) {
        courseRepository.save((Session) o);
    }
}
