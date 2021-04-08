package com.comp3004.CMS.base.deliverables.factory;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Exam;
import com.comp3004.CMS.base.deliverables.Deliverable;

import java.time.LocalDate;

public class ExamFactory extends DeliverableFactory{
    @Override
    public Deliverable createDeliverable(String type, Session c) {

        if(type.equals("midterm")){
            return new Exam("Midterm Exam", 0.2, LocalDate.now(), c);
        }
        else if(type.equals("final")){
            return new Exam("Final Exam", 0.4, LocalDate.now().plusDays(30), c);
        }

        return null;
    }
}
