package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Admin;
import com.comp3004.CMS.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping ("/addStudent")
    public String addStudent() {
        if (adminService.loggedIn()){
            return "redirect:/addStudent.html";
        }
        return "redirect:/addStudent.html";
        //return "Error: You have to be ADMIN to add student.";
        //Redirect to a page with Error 404 (html)
    }



}
