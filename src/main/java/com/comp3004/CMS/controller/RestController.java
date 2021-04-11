package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.*;
import com.comp3004.CMS.repository.GradeRepository;
import com.comp3004.CMS.sdc.GradePoint;
import com.comp3004.CMS.sdc.GradingFilter;
import com.comp3004.CMS.sdc.StudentFilter;
import com.comp3004.CMS.sdc.StudentGradeDTO;
import com.comp3004.CMS.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    @Qualifier("CourseFilter")
    private GradingFilter gradingFilter;

    public static String role = "none";


    @GetMapping("/")
    public String greeting() {
        return "redirect:/login";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(@RequestParam String program){
//        Long l1 = Long.valueOf(10000);
//        Long l2 = Long.valueOf(10001);
//        List<Long> l= Arrays.asList(l1, l2);

        String r = "";

//        for(StudentGrade sg: gradeRepository.findStudentGradesByStudentIdIn(l)){
//            r = r + sg.getGrade() + sg.getStudent().toString();
//
//        }

        r = gradingFilter.inProgram(program).toString();

        return r;
    }





    @GetMapping("/login")
    public String getLogin(Model model){
        return "redirect:/login.html";
    }

    @PostMapping("/login")
    public String userlogin(@RequestParam String username, @RequestParam String password, @RequestParam String role){

        String userRole = authorization(role, username, password);
        if (userRole.equals("student")){
            return "redirect:/student?username="+username;
        }
        if (userRole.equals("professor")){
            return "redirect:/professor.html";
        }
        if (userRole.equals("admin")){
            return "redirect:/admin.html";
        }
        return "redirect:/accessDenied.html";
    }

    @PostMapping("/logout")
    @ResponseBody
    public String userlogout(@RequestParam String username, @RequestParam String role){
        if(role.equals("student")){
            if(studentService.logout(username)){
                RestController.role = "none";
                return "Logout Succeed. ";
            }
        }
        if(role.equals("admin")){
            if(adminService.logout()){
                RestController.role = "none";
                return "Logout Succeed. ";
            }
        }
        return "Logout Failed. ";
    }


    public String authorization(String role, String username, String password){

        if(role.equals("student")){
            if(studentService.login(username, password)){
                RestController.role = "student";
                return "student";
            }
        }
        if(role.equals("professor")){
            if(professorService.login(username, password)){
                RestController.role = "professor";
                return "professor";
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
