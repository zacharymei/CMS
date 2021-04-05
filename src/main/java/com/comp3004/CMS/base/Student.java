package com.comp3004.CMS.base;

import javax.persistence.*;
import java.util.*;


@Entity
@SequenceGenerator(name="userGen", sequenceName = "studentSeq", initialValue=10000, allocationSize=9999)
public class Student extends User{



    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userGen")
    private long id;

    private String firstName;

    private String lastName;

    private String program;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.ALL
            })
    @JoinTable(joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Session> courses;







    public Student(){
        super();
        this.courses = new HashSet<>();
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

    public Set<Session> getCourses() { return courses; }



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

    public void registerCourse(Session c){
        courses.add(c);
        addObserver(c);
        setChanged();
        notifyObservers("register");
    }

    public void dropCourse(Session c){
        courses.remove(c);
        setChanged();
        notifyObservers("drop");
        deleteObserver(c);
    }

    public void submitDeliverable(Deliverable d){
        // soon
    }

}
