package com.comp3004.CMS.base;

import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.base.deliverables.factory.DeliverableFactory;
import com.comp3004.CMS.visitor.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@Entity
public class Session extends Course implements Observer {

    private String term;
    private String session;
    private String time;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="professor")
    private Professor professor;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Student> registered;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Deliverable> deliverables;

    @JsonIgnore
    @OneToMany(mappedBy = "session")
    private Set<StudentGrade> studentGrades;




    public void register(Student s){
        registered.add(s);
    }

    public void drop(Student s) { registered.remove(s); }







    public Session(){
        super();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Student> getRegistered(){
        return this.registered;
    }

    public void setProgram(String program){
        super.setProgram(program);
    }

    public void setNumber(String number){
        super.setNumber(number);
    }

    public Set<Deliverable> getDeliverables() {
        return deliverables;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
        this.addObserver(professor);
        setChanged();
        notifyObservers("Session");

    }

    public void setRegistered(Set<Student> registered) {
        this.registered = registered;
    }

    public void setDeliverables(Set<Deliverable> deliverables) {
        this.deliverables = deliverables;
    }

    public Set<StudentGrade> getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades(Set<StudentGrade> studentGrades) {
        this.studentGrades = studentGrades;
    }

    @Override()
    public void update(Observable o, Object arg){
        if(arg.equals("register")) {
            register((Student) o);
        }
        if(arg.equals("drop")){
            drop((Student) o);
        }
        if(arg.equals("assign")){
            professor = (Professor)o;
        }
        if(arg.equals("remove")){
            professor = null;
        }
        if(arg.equals("deleteStudent")){
            registered.remove((Student) o);
        }
        if(arg.equals("StudentGrade")){
            studentGrades.add((StudentGrade) o);
        }
        if(arg.equals("Deliverable")){
            deliverables.add((Deliverable) o);
        }
    }

    public String accept(Visitor v){
        return v.visit(this);
    }

}
