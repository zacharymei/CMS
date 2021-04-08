package com.comp3004.CMS.base.deliverables;

import com.comp3004.CMS.base.Course;
import com.comp3004.CMS.base.Session;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Inheritance
public class Deliverable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;

    private double weight;
    private LocalDate due;

    @ElementCollection
    private List<String> questions;

    @ManyToOne
    @JoinColumn(name="deliverable_id")
    private Session course;


    //<editor-fold desc="Getter Setter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public void setCourse(Session c){
        this.course = c;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "Deliverable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", due=" + due +
                ", course=" + course +
                '}';
    }
}
