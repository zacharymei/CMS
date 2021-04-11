package com.comp3004.CMS.base.deliverables.factory;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.base.deliverables.Exam;
import com.comp3004.CMS.base.deliverables.Lecture;

import java.time.LocalDate;

public class LectureFactory extends DeliverableFactory{
    @Override
    public Deliverable createDeliverable(String type, Session c) {

        if(type.equals("video")){
            return new Lecture("Lecture video", 0, null, c);
        }
        else if(type.equals("note")){
            return new Lecture("Lecture Notes", 0, null, c);
        }

        return null;
    }
}
