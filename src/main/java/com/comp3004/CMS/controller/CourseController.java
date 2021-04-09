package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.services.AdminService;
import com.comp3004.CMS.services.CourseService;
import com.comp3004.CMS.services.SessionService;
import com.comp3004.CMS.controller.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.Oneway;
import java.util.List;

@Controller
public class CourseController{

    @Autowired
    CourseService courseService;
    @Autowired
    SessionService sessionService;
    @Autowired
    private AdminService adminService;

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


        if(adminService.loggedIn()){
            if(sessionService.addCourse(p, number, session, term, time)){ return "Course generated"; }
        }

        return "Course failed";
    }

    @GetMapping("/courseRegistered")
    @ResponseBody
    public String courseRegistered(@RequestParam long cid){
        return sessionService.findById(cid).getRegistered().toString();
    }

}
