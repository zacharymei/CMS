package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Course;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.services.AdminService;
import com.comp3004.CMS.services.SessionService;
import com.comp3004.CMS.services.StudentService;
import com.comp3004.CMS.visitor.LogInfo;
import com.comp3004.CMS.visitor.Visitor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
public class StudentController{

    @Autowired
    private StudentService studentService;
    @Autowired
    private  RestController restController;

    @Autowired
    SessionService sessionService;
    @Autowired
    AdminService adminService;


    @PostMapping ("/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam("firstname") String fn, @RequestParam("lastname") String ln, @RequestParam("program") String program, @RequestParam("password") String pw){

        if(studentService.addStudent(fn, ln, program, pw)){
            return "Student generated";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Add Student failed");
        //return "Add Student failed";
    }

    @DeleteMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(@RequestParam long id){

        if(studentService.deleteStudent(id)){
            return "Delete Succeed. ";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Delete Student failed");
        //return "Delete Student failed";
    }

    @GetMapping("/student")
    public String student(@RequestParam String username, Model model){
        Student s = studentService.getStudentByUsername(username);
        model.addAttribute("student", s);
        return "student";
    }

    @GetMapping("/register")
    public String register(Model model){
        Map<String,String> ja = new HashMap();
        Visitor v1 = new LogInfo();
        List<Session> allSession = sessionService.findAll();
        for (Session s:allSession){
            ja.put(Long.toString(((Course)(s)).getId()),s.accept(v1));
        }
        model.addAttribute("allSession", ja);
        return "register";
    }


    @GetMapping("/students")
    @ResponseBody
    public String getStudents(){
        return studentService.showAll();
    }

    @PostMapping("/register")
    @ResponseBody
    public String studentRegister(@RequestParam("sid") long sid, @RequestParam("cid") long cid){


        Session c = sessionService.findById(cid);
        Student s = studentService.findById(sid);

        if(studentService.register(sid, c)){
            return "Registration succeed. ";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Registration failed. ");
        //return "Registration failed. ";
    }

    @PostMapping("/drop")
    @ResponseBody
    public String studentDrop(@RequestParam("sid") long sid, @RequestParam("cid") long cid){
        Session c = sessionService.findById(cid);
        Student s = studentService.findById(sid);

        if(studentService.drop(sid, c)){
            return "Successfully drop";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "fail drop");
        //return "fail drop";
    }

    @GetMapping("/studentRegistered")
    @ResponseBody
    public String studentRegistered(@RequestParam long sid){
        return studentService.studentRegistered(sid).toString();
    }


}
