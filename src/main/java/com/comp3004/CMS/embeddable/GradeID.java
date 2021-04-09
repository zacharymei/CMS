package com.comp3004.CMS.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GradeID implements Serializable {

    private long studentID;
    private long deliverableID;

    public GradeID(){

    }

    public GradeID(long sid, long did){
        this.studentID = sid;
        this.deliverableID = did;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public long getDeliverableID() {
        return deliverableID;
    }

    public void setDeliverableID(long deliverableID) {
        this.deliverableID = deliverableID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeID gradeID = (GradeID) o;
        return studentID == gradeID.studentID && deliverableID == gradeID.deliverableID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, deliverableID);
    }

    @Override
    public String toString() {
        return "GradeID{" +
                "studentID=" + studentID +
                ", deliverableID=" + deliverableID +
                '}';
    }
}
