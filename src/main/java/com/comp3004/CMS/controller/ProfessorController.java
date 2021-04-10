package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Controller
public class ProfessorController{

    @Autowired
    ProfessorService professorService;
    @Autowired
    DeliverableService deliverableService;
    @Autowired
    SessionService sessionService;
    @Autowired
    RestController restController;




    @PostMapping("/addProfessor")
    @ResponseBody
    public String addProfessor(@RequestParam String firstname,@RequestParam String lastname,@RequestParam String program,@RequestParam String password){
        if(professorService.addProfessor(firstname, lastname, program, password)){
            return "Professor Generated. ";
        }

        return "Professor Failed";
    }

    @GetMapping("/professors")
    @ResponseBody
    public List<Professor> professors(){
        return professorService.findAll();
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


    @PostMapping("/createDeliverable")
    @ResponseBody
    public String createDeliverable(@RequestParam long cid, @RequestParam String deliverable, @RequestParam String type){
        if(deliverableService.createDeliverable(cid, deliverable, type)){
            return "Deliverable created. ";
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Deliverable create failed.");
        //return "Deliverable create failed. ";
    }

    @GetMapping("/deliverables")
    @ResponseBody
    public List<Deliverable> deliverables(){
        return deliverableService.findAll();
    }

    @GetMapping("/deliverable")
    @ResponseBody
    public String deliverables(@RequestParam long cid){
        return sessionService.findById(cid).getDeliverables().toString();
    }

    @PostMapping("/gradeDeliverable")
    @ResponseBody
    public String gradeDeliverable(@RequestParam long sid, @RequestParam long did, @RequestParam double grade){

        if(deliverableService.gradeDeliverable(sid, did, grade)){
            return "Grade Successful";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Grade Failed");
        //return "Grade Failed";
    }




}
