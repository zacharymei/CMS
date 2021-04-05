package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.services.CourseService;
import com.comp3004.CMS.services.SessionService;
import com.comp3004.CMS.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        sessionService.save(c);

        return "Successfully register";
    }

    @GetMapping("/drop")
    @ResponseBody
    public String studentDrop(@RequestParam("sid") long sid, @RequestParam("cid") long cid){
        Session c = sessionService.findById(cid);
        Student s = studentService.findById(sid);

        studentService.drop(sid, c);
        sessionService.save(c);
        return "Successfully drop";
    }

    @PostMapping("/login")
    @ResponseBody
    public String userlogin(@RequestParam String username, @RequestParam String password, @RequestParam String role){
        return authorization(role, username, password);
    }

    public String authorization(String role, String username, String password){

        if(role.equals("student")){
            studentService.login(username, password);
            return "student";
        }


        return "none";
    }


}
