package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.services.CourseService;
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

    @GetMapping("/")
    @ResponseBody
    public String greeting() {
        return "hello world";
    }

    @GetMapping("/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam("firstname") String fn, @RequestParam("lastname") String ln, @RequestParam("program") String program, @RequestParam("password") String pw){
        if(studentService.addStudent(fn, ln, program, pw)){ return "Student generated"; }
        return "Student failed";
    }

    @GetMapping("/students")
    @ResponseBody
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @GetMapping("/addCourse")
    @ResponseBody
    public String addCourse(@RequestParam("program") String p, @RequestParam("number") String number){
        if(courseService.addCourse(p, number)){ return "Course generated"; }
        return "Course failed";
    }


    @GetMapping("/courses")
    @ResponseBody
    public List<Course> getCourses(){
        return courseService.findAll();
    }

    @GetMapping("/register")
    @ResponseBody
    public String studentRegister(@RequestParam("sid") long sid, @RequestParam("cid") long cid){
        Course c = courseService.findById(cid);
        Student s = studentService.findById(sid);

        studentService.register(sid, c);
        courseService.register(cid, s);

        //System.out.println(studentService.findById(sid).getCourses().toString());
        //System.out.println(courseService.findById(cid).getRegistered().toString());
        return "Successfully register";
    }

}
