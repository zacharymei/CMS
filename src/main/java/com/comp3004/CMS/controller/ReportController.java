package com.comp3004.CMS.controller;

import com.comp3004.CMS.sdc.GradePoint;
import com.comp3004.CMS.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/filter/program")
    @ResponseBody
    public Set<GradePoint> filterProgram(@RequestParam String program, @RequestParam String student, @RequestParam String professor, @RequestParam String course){

        Set<String> targets = new HashSet<>();
        if(student.equals("on")){ targets.add("student"); }
        if(professor.equals("on")){ targets.add("professor"); }
        if(course.equals("on")){ targets.add("course"); }

        if(targets.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty target");
        }

        return reportService.filterByProgram(program, targets);

    }

}
