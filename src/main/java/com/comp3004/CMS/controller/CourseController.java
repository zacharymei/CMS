package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.services.*;
import com.comp3004.CMS.controller.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.Oneway;
import java.util.List;

@Controller
public class CourseController{

    @Autowired
    CourseService courseService;
    @Autowired
    SessionService sessionService;
    @Autowired
    DeliverableService deliverableService;
    @Autowired
    GradeService gradeService;

    @GetMapping("/courses")
    @ResponseBody
    public List<Session> getCourses(){
        return courseService.findAll();
    }

    @PostMapping("/addCourse")
    @ResponseBody
    public String addCourse(@RequestParam(name="program") String p,
                            @RequestParam("number") String number,
                            @RequestParam("session") String session,
                            @RequestParam("term") String term,
                            @RequestParam(name="time", required = false) String time){



        if(sessionService.addCourse(p, number, session, term, time)){ return "Course generated"; }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Course failed");
        //return "Course failed";
    }

    @GetMapping("/courseRegistered")
    @ResponseBody
    public String courseRegistered(@RequestParam long cid){
        return sessionService.findById(cid).getRegistered().toString();
    }

    @GetMapping("/studentGrades")
    @ResponseBody
    public List<StudentGrade> studentGrades(){
        return gradeService.findAll();
    }

}
