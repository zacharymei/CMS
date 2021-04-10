package com.comp3004.CMS.base.deliverables;

import com.comp3004.CMS.base.Session;

import javax.persistence.Entity;
import java.time.*;

@Entity
public class Assignment extends Deliverable{

    public Assignment(){

    }

    public Assignment(String name, double weight, LocalDate due, Session c){
        super();
        super.setName(name);
        super.setWeight(weight);
        super.setDue(due);
        super.setCourse(c);
        super.addObserver(c);

        super.setChanged();
        super.notifyObservers("Deliverable");
    }

}
