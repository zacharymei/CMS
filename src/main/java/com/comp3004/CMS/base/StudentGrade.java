package com.comp3004.CMS.base;

import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.embeddable.GradeID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class StudentGrade {

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
    Deliverable deliverable;

    public StudentGrade(){

    }

    public StudentGrade(GradeID id, double grade, Student s, Deliverable d){
        this.id = id;
        this.grade = grade;
        this.student = s;
        this.deliverable = d;
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
