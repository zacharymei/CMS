package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Admin;
import com.comp3004.CMS.services.AdminService;
import com.comp3004.CMS.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller

public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    ProfessorService professorService;


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
