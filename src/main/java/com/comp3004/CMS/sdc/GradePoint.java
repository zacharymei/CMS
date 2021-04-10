package com.comp3004.CMS.sdc;

import com.comp3004.CMS.base.*;

public class GradePoint {

    private double grade;
    private Student student = null;
    private Professor professor = null;
    private Course course = null;

    public GradePoint(){

    }

    public GradePoint(double grade, Student s, Professor p, Course c){
        this.grade = grade;
        this.student = s;
        this.professor = p;
        this.course = c;
    }

    public GradePoint(double grade, Student s){
        this.grade = grade;
        this.student = s;
    }

    public GradePoint(double grade, Professor p){
        this.grade = grade;
        this.professor = p;
    }

    public GradePoint(double grade, Course c){
        this.grade = grade;
        this.course = c;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "GradePoint{" +
                "grade=" + grade +
                ", student=" + student +
                ", professor=" + professor +
                ", course=" + course +
                '}';
    }
}
