package com.comp3004.CMS.base;

import javax.persistence.*;

@Entity
public class Student extends User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String program;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
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
