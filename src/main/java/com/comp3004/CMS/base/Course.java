package com.comp3004.CMS.base;

import javax.persistence.*;
import java.util.*;

@MappedSuperclass
public abstract class Course extends Observable {

    @Id
    @SequenceGenerator(name="courseSeq", initialValue=10000)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="courseSeq")
    private long id;

    private String program;
    private String number;



    public Course() {

    }

    public long getId() { return id; }

    public String getProgram() { return program; }

    public void setProgram(String program) { this.program = program; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }




    @Override
    public String toString() {
        return "Course{" +
                "Id=" + id +
                ", program='" + program + '\'' +
                ", number='" + number + '\'' +
                '}';
    }


}
