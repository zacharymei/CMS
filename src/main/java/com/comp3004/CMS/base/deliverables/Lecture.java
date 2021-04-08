package com.comp3004.CMS.base.deliverables;

import com.comp3004.CMS.base.Session;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Lecture extends Deliverable{

    public Lecture(){

    }

    public Lecture(String name, double weight, LocalDate due, Session c){
        super();
        super.setName(name);
        super.setWeight(weight);
        super.setDue(due);
        super.setCourse(c);
    }

}
