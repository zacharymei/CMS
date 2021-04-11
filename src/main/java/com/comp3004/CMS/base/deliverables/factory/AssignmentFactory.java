package com.comp3004.CMS.base.deliverables.factory;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Assignment;
import com.comp3004.CMS.base.deliverables.Deliverable;

import java.time.LocalDate;

public class AssignmentFactory extends DeliverableFactory{


    @Override
    public Deliverable createDeliverable(String type, Session c) {
        if(type.equals("small")){
            return new Assignment("Small assignment", 0.1, LocalDate.now().plusDays(7), c);
        }
        else if(type.equals("big")){
            return new Assignment("Large assignment", 0.2, LocalDate.now().plusDays(20), c);
        }
        return null;
    }
}
