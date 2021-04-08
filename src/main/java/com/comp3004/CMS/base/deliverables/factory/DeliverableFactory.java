package com.comp3004.CMS.base.deliverables.factory;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Assignment;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.base.deliverables.Exam;
import com.comp3004.CMS.base.deliverables.Lecture;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public abstract class DeliverableFactory {

    public abstract Deliverable createDeliverable(String type, Session c);

    public Deliverable modifyWeight(Deliverable d, double weight){
        d.setWeight(weight);
        return null;
    }

    public Deliverable modifyDue(Deliverable d, LocalDate date){
        d.setDue(date);
        return null;
    }

    //abstract Deliverable modifyContent(Deliverable d, String content);

    public Deliverable addQuestion(Deliverable d, String question){
        d.getQuestions().add(question);
        return null;
    }


}
