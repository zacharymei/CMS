package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.StudentRepository;
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
    private StudentRepository studentRepository;

    @GetMapping("/")
    @ResponseBody
    public String greeting() {
        return "hello world";
    }

    @GetMapping("/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam Map<String, String> customQuery){
        Student s = new Student();
        s.setFirstName(customQuery.get("firstname"));
        s.setLastName(customQuery.get("lastname"));
        s.setProgram(customQuery.get("program"));
        ((User)s).setUsername(customQuery.get("firstname") + customQuery.get("lastname"));
        ((User)s).setPassword(customQuery.get("password"));
        //Student s = new Student(customQuery.get("firstname"), customQuery.get("lastname"), customQuery.get("program"), customQuery.get("password"));
        studentRepository.save(s);
        return "Saved";
    }

    @GetMapping("/students")
    @ResponseBody
    public Iterable<Student> getStudents(){
        return studentRepository.findAll();
    }

}
