package com.comp3004.CMS.base;

import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.base.deliverables.factory.DeliverableFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@Entity
public class Session extends Course implements Observer {

    private String term;
    private String session;
    private String time;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Professor professor;


    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Student> registered;

    @OneToMany(mappedBy = "course")
    private Set<Deliverable> deliverables;



    public void register(Student s){
        registered.add(s);
    }

    public void drop(Student s) { registered.remove(s); }







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

    public Set<Deliverable> getDeliverables() {
        return deliverables;
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
    }

}
