package com.comp3004.CMS.base;

import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.embeddable.GradeID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Observable;

@Entity
public class StudentGrade extends Observable {

    @EmbeddedId
    private GradeID id;

    private double grade;

    @ManyToOne
    //@MapsId("studentID")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    //@MapsId("deliverableID")
    @JoinColumn(name = "deliverable_id")
    //@JoinColumn(name = "course_id", referencedColumnName = "course_id")
    Deliverable deliverable;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Session session;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    Professor professor;


    public StudentGrade(){

    }

    public StudentGrade(GradeID id, double grade, Student s, Deliverable d){
        this.id = id;
        this.grade = grade;
        this.student = s;
        addObserver(s);
        this.deliverable = d;
        addObserver(d);
        this.session = deliverable.getCourse();
        addObserver(session);
        this.professor = session.getProfessor();
        addObserver(professor);
        setChanged();
        notifyObservers("StudentGrade");
    }


    public GradeID getId() {
        return id;
    }

    public void setId(GradeID id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Deliverable getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(Deliverable deliverable) {
        this.deliverable = deliverable;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "id=" + id +
                ", grade=" + grade +
                ", student=" + student +
                ", deliverable=" + deliverable +
                '}';
    }
}
