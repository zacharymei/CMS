package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.services.CourseService;
import com.comp3004.CMS.services.DeliverableService;
import com.comp3004.CMS.services.SessionService;
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


}
