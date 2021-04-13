package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Admin;
import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.services.AdminService;
import com.comp3004.CMS.services.ProfessorService;
import com.comp3004.CMS.services.SessionService;
import com.comp3004.CMS.visitor.LogInfo;
import com.comp3004.CMS.visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Controller

public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    public ProfessorService professorService;
    @Autowired
    public SessionService sessionService;

    @GetMapping ("/addStudent")
    public String addStudent() {
        if (adminService.loggedIn()){
            return "redirect:/addStudent.html";
        }
        return "redirect:/accessDenied.html";
        //return "Error: You have to be ADMIN to add student.";
        //Redirect to a page with Error 404 (html)
    }

    @GetMapping ("/addProfessor")
    public String addProfessor() {
        if (adminService.loggedIn()){
            return "redirect:/addProfessor.html";
        }
        return "redirect:/accessDenied.html";
        //return "Error: You have to be ADMIN to add student.";
        //Redirect to a page with Error 404 (html)
    }

    @GetMapping ("/addCourse")
    public String addCourse() {
        if (adminService.loggedIn()){
            return "redirect:/addCourse.html";
        }
        return "redirect:/accessDenied.html";
        //return "Error: You have to be ADMIN to add student.";
        //Redirect to a page with Error 404 (html)
    }

    @GetMapping("assignProfessor")
    public String getAssignProfessor(Model model){
        Map<String, String> prof= new HashMap<>();
        Map<String, String> courses = new HashMap<>();
        Visitor log = new LogInfo();
        for(Professor p : professorService.findAll()){
            prof.put(Long.toString(p.getId()), p.accept(log));
        }
        for(Session c : sessionService.findAll()){
            courses.put(Long.toString(c.getId()), c.accept(log));
        }
        model.addAttribute("profs", prof);
        model.addAttribute("courses", courses);
        return "assignProfessor";
    }

    @PostMapping("/assignProfessor")
    @ResponseBody
    public String assignProfessor(@RequestParam long pid, @RequestParam long cid){
        if(professorService.assign(pid, cid)){
            return "Professor Assigned. ";
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Assign Failed");
        //return "Assign Failed";
    }



}
