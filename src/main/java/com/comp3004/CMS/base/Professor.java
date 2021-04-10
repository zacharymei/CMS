package com.comp3004.CMS.base;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

@Entity
@SequenceGenerator(name="userGen", sequenceName = "professorSeq", initialValue=10000, allocationSize=9999)
public class Professor extends User{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="userGen")
    private long id;

    private String firstName;

    private String lastName;

    private String program;

    @JsonIgnore
    @OneToMany(mappedBy="professor")
    private Set<Session> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private Set<StudentGrade> studentGrades;

    //constructor
    public Professor(){
        super();
        this.courses = new HashSet<>();
    }

    //getter and setter function
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Set<Session> getCourses() {
        return courses;
    }

    public void setCourses(Set<Session> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", program='" + program + '\'' +
                ", courses=" + courses +
                '}';
    }


    public void removeCourse(Session c){
        courses.remove(c);
        setChanged();
        notifyObservers("remove");
        deleteObserver(c);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("StudentGrade")){
            studentGrades.add((StudentGrade) o);
        }
        if(arg.equals("Session")){
            courses.add((Session) o);
        }
        setChanged();
        notifyObservers();
    }
}
