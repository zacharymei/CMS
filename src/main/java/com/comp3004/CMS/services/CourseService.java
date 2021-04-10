package com.comp3004.CMS.services;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Service
@Transactional
public class CourseService implements Observer {
    @Autowired
    private CourseRepository courseRepository;

    public List<Session> findAll(){
        return (List<Session>) courseRepository.findAll();
    }

    public Course findById(long id){
        return courseRepository.findById(id);
    }


    @Override
    public void update(Observable o, Object arg) {
        courseRepository.save((Session) o);
    }
}
