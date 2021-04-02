package com.comp3004.CMS.base;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="native")
    private long Id;

    private String program;
    private String number;
    private long professor;

    @ManyToMany(mappedBy = "registered", fetch = FetchType.LAZY)
    private Set<Student> registered = new HashSet<>();

    public long getId() { return Id; }

    public String getProgram() { return program; }

    public void setProgram(String program) { this.program = program; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public long getProfessor() { return professor; }

    public void setProfessor(long professor) { this.professor = professor; }

    public Set<Student> getRegistered() { return registered; }

    public void setRegistered(Set<Student> registered) { this.registered = registered; }
}
