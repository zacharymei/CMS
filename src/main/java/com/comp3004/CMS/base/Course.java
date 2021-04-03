package com.comp3004.CMS.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @SequenceGenerator(name="courseSeq", initialValue=30000, allocationSize=9999)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="courseSeq")
    private long id;

    private String program;
    private String number;
    private long professor;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Student> registered;


    public Course() {
        this.registered = new HashSet<>();
    }

    public long getId() { return id; }

    public String getProgram() { return program; }

    public void setProgram(String program) { this.program = program; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public long getProfessor() { return professor; }

    public void setProfessor(long professor) { this.professor = professor; }

    public Set<Student> getRegistered() { return registered; }


    @Override
    public String toString() {
        return "Course{" +
                "Id=" + id +
                ", program='" + program + '\'' +
                ", number='" + number + '\'' +
                ", professor=" + professor +
                ", registered=" + registered.toString() +
                '}';
    }

    public void register(Student s){
        registered.add(s);
    }
}
