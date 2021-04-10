package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfessorController {

    @Autowired
    DeliverableService deliverableService;
    @Autowired
    SessionService sessionService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AdminService adminService;

    @PostMapping ("/addProfessor")
    @ResponseBody
    public String addProfessor(@RequestParam("firstname") String fn, @RequestParam("lastname") String ln, @RequestParam("program") String program, @RequestParam("password") String pw){
        if(adminService.getLoggedin()){
            if(professorService.addProfessor(fn, ln, program, pw)){ return "Professor generated"; }
        }else{
            return "Permission denied. ";
        }
        return "Add Professor failed";
    }

    @PostMapping("/createDeliverable")
    @ResponseBody
    public String createDeliverable(@RequestParam long cid, @RequestParam String deliverable, @RequestParam String type){
        deliverableService.createDeliverable(cid, deliverable, type);
        return "Deliverable created. ";
    }

    @GetMapping("/deliverable")
    @ResponseBody
    public String deliverables(@RequestParam long cid){
        return sessionService.findById(cid).getDeliverables().toString();
    }

    @PostMapping("/gradeDeliverable")
    @ResponseBody
    public String gradeDeliverable(@RequestParam long sid, @RequestParam long did, @RequestParam double grade){

        deliverableService.gradeDeliverable(sid, did, grade);

        return "Grade Successful";
    }


}
