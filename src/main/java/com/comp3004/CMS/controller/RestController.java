package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.services.AdminService;
import com.comp3004.CMS.services.CourseService;
import com.comp3004.CMS.services.SessionService;
import com.comp3004.CMS.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    @ResponseBody
    public String greeting() {
        return "hello world";
    }




    @PostMapping("/register")
    @ResponseBody
    public String studentRegister(@RequestParam("sid") long sid, @RequestParam("cid") long cid){
        Session c = sessionService.findById(cid);
        Student s = studentService.findById(sid);

        studentService.register(sid, c);
        sessionService.save(c);

        return "Successfully register";
    }

    @PostMapping("/drop")
    @ResponseBody
    public String studentDrop(@RequestParam("sid") long sid, @RequestParam("cid") long cid){
        Session c = sessionService.findById(cid);
        Student s = studentService.findById(sid);

        studentService.drop(sid, c);
        sessionService.save(c);
        return "Successfully drop";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "redirect:/login.html";
    }

    @PostMapping("/login")
    @ResponseBody
    public String userlogin(@RequestParam String username, @RequestParam String password, @RequestParam String role){

        System.out.println(username);
        System.out.println(password);
        System.out.println(role);

        return authorization(role, username, password);
    }

    @PostMapping("/logout")
    @ResponseBody
    public String userlogout(@RequestParam String username, @RequestParam String role){
        if(role.equals("student")){
            if(studentService.logout(username)){
                return "Logout Succeed. ";
            }
        }
        if(role.equals("admin")){
            if(adminService.logout()){
                return "Logout Succeed. ";
            }
        }
        return "Logout Failed. ";
    }


    public String authorization(String role, String username, String password){

        if(role.equals("student")){
            if(studentService.login(username, password)){
                return "student";
            }
        }
        if(role.equals("admin")){
            if(adminService.login(username, password)){
                return "admin";
            }
        }


        return "none";
    }


}
