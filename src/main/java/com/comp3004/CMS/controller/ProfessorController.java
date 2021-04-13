package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.services.*;
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
public class ProfessorController {

    @Autowired
    DeliverableService deliverableService;
    @Autowired
    SessionService sessionService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    StudentService studentService;

    @PostMapping ("/addProfessor")
    @ResponseBody
    public String addProfessor(@RequestParam("firstname") String fn, @RequestParam("lastname") String ln, @RequestParam("program") String program, @RequestParam("password") String pw){

        if(professorService.addProfessor(fn, ln, program, pw)){
            return "Professor generated";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Add Professor failed");
        //return "Add Student failed";
    }

    @GetMapping("/professor")
    public String professor(@RequestParam String username, Model model){
        Professor p = professorService.getProfessorByUsername(username);
        model.addAttribute("professor", p);
        return "professor";
    }

    @GetMapping("/createDeliverable")
    public String getCreateDeliverable(){
        return "redirect:/addDeliverable.html";
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

    @GetMapping("/gradeDeliverable")
    public String getGradeDeliverable(Model model){
        Map<String, String> students= new HashMap<>();
        Map<String, String> deliverables = new HashMap<>();
        Visitor log = new LogInfo();
        for(Student s : studentService.findAll()){
            students.put(Long.toString(s.getId()), s.accept(log));
        }
        for(Deliverable d : deliverableService.findAll()){
            deliverables.put(Long.toString(d.getId()), d.getName());
        }
        model.addAttribute("students", students);
        model.addAttribute("deliverables", deliverables);
        return "gradeDeliverable";
    }


}
