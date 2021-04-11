package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.services.CourseService;
import com.comp3004.CMS.services.DeliverableService;
import com.comp3004.CMS.services.ProfessorService;
import com.comp3004.CMS.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ProfessorController {

    @Autowired
    DeliverableService deliverableService;
    @Autowired
    SessionService sessionService;
    @Autowired
    private ProfessorService professorService;

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
