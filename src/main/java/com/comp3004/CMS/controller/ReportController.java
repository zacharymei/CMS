package com.comp3004.CMS.controller;

import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.sdc.GradePoint;
import com.comp3004.CMS.services.ReportService;
import com.comp3004.CMS.visitor.LogInfo;
import com.comp3004.CMS.visitor.Visitor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.font.GraphicAttribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("filter")
    public String getFilter(){
        return "filter";
    }


    @PostMapping ("/filter")
    public String filterProgram(@RequestParam String filter, @RequestParam String value,
                                @RequestParam(name = "student", required = false, defaultValue = "off") String student,
                                @RequestParam(name = "professor", required = false, defaultValue = "off") String professor,
                                @RequestParam(name = "course", required = false, defaultValue = "off") String course,
                                Model model){


        if(filter.equals("Program")){
            Set<String> targets = new HashSet<>();
            if(student.equals("on")){ targets.add("student"); }
            if(professor.equals("on")){ targets.add("professor"); }
            if(course.equals("on")){ targets.add("course"); }

            if(targets.isEmpty()){
                return "filter";
            }
            Set<GradePoint> gradePoints = reportService.filterByProgram(value, targets);
            JSONArray out = new JSONArray();

            Visitor logInfo = new LogInfo();
            for(GradePoint sg : gradePoints){
                String grade = Double.toString(sg.getGrade());
                String s = sg.getStudent().accept(logInfo);
                String p = "Professor: not assigned";
                if(sg.getProfessor() != null){
                    p = sg.getProfessor().accept(logInfo);
                }
                String c = sg.getCourse().accept(logInfo);

                JSONObject point = new JSONObject();
                point.put("grade", sg.getGrade());
                point.put("student", s);
                point.put("professor", p);
                point.put("course", c);

                out.put(point);
            }

            model.addAttribute("gradePoints", out.toString());
        }


        return "filter";

    }

}
