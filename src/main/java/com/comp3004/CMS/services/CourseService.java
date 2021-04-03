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

    public List<Session> findAll(){
        return (List<Session>) courseRepository.findAll();
    }

    public Course findById(long id){
        return courseRepository.findById(id);
    }





}
