package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.services.CourseService;
import com.comp3004.CMS.services.SessionService;
import com.comp3004.CMS.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

@Controller
public class RestController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/")
    @ResponseBody
    public String greeting() {
        return "hello world";
    }




    @GetMapping("/register")
    @ResponseBody
    public String studentRegister(@RequestParam("sid") long sid, @RequestParam("cid") long cid){
        Session c = sessionService.findById(cid);
        Student s = studentService.findById(sid);

        studentService.register(sid, c);
        sessionService.register(cid, s);

        return "Successfully register";
    }



}
