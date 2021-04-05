package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.services.AdminService;
import com.comp3004.CMS.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentController{

    @Autowired
    private StudentService studentService;
    @Autowired
    private  RestController restController;
    @Autowired
    private AdminService adminService;

    @GetMapping("/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam("firstname") String fn, @RequestParam("lastname") String ln, @RequestParam("program") String program, @RequestParam("password") String pw){
        if(adminService.getLoggedin()){
            if(studentService.addStudent(fn, ln, program, pw)){ return "Student generated"; }
        }else{
            return "Permission denied. ";
        }



        return "Add Student failed";
    }

    @DeleteMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(@RequestParam long id){
        if(adminService.getLoggedin()){
            if(studentService.deleteStudent(id)){
                return "Delete Succeed. ";
            }
        }else{
            return "Permission denied. ";
        }

        return "Delete Student failed";
    }


    @GetMapping("/students")
    @ResponseBody
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @GetMapping("/studentRegistered")
    @ResponseBody
    public String studentRegistered(@RequestParam long sid){
        return studentService.studentRegistered(sid).toString();
    }


}
