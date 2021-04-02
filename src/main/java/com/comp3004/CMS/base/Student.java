package com.comp3004.CMS.base;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student extends User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="native")
    private long id;
    private String firstName;
    private String lastName;
    private String program;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })
    @JoinTable(
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    public Set<Course> registered = new HashSet<>();

    public Student(){
        super();
    }

    public Student(String fn, String ln, String program, String password){
        super(fn+ln, password);
        this.firstName = fn;
        this.lastName =ln;
        this.program = program;

    }

    // Getter Setter

    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getProgram(){ return program; }
    public long getId(){ return id; }

    public Set<Course> getRegistered() { return registered; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setProgram(String newProgram){
        this.program = newProgram;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", program='" + program + '\'' +
                '}';
    }

    public void registerCourse(Course c){
        // interact with DB
    }

    public void dropCourse(Course c){
        // interact with DB
    }

    public void submitDeliverable(Deliverable d){
        // soon
    }

}
