package com.comp3004.CMS.base;

import javax.persistence.*;
import java.util.*;

@Entity
public class Session extends Course implements Observer {

    private String term;
    private String session;
    private String time;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "professor_id", referencedColumnName = "id")
//    private Professor professor;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Student> registered;


    public void register(Student s){
        registered.add(s);
    }







    public Session(){
        super();
        this.registered = new HashSet<>();
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

    @Override
    public void update(Observable o, Object arg) {
        register((Student) o);
    }
}
