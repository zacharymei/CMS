package com.comp3004.CMS.services;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.base.deliverables.factory.AssignmentFactory;
import com.comp3004.CMS.base.deliverables.factory.DeliverableFactory;
import com.comp3004.CMS.base.deliverables.factory.ExamFactory;
import com.comp3004.CMS.base.deliverables.factory.LectureFactory;
import com.comp3004.CMS.controller.RestController;
import com.comp3004.CMS.embeddable.GradeID;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.repository.DeliverableRepository;
import com.comp3004.CMS.repository.GradeRepository;
import com.comp3004.CMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DeliverableService implements Observer{

    @Autowired
    DeliverableRepository deliverableRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RestController restController;

    DeliverableFactory deliverableFactory;



    public List<Deliverable> findAll(){ return deliverableRepository.findAll(); }

    public boolean createDeliverable(long cid, String d, String type){

        if(!RestController.role.equals("professor")){
            return false;
        }


        Session c = courseRepository.findById(cid);
        Deliverable deliverable = null;

        if(d.equals("assignment")){
            deliverableFactory = new AssignmentFactory();
            deliverable = deliverableFactory.createDeliverable(type, c);

        }
        if(d.equals("exam")){
            deliverableFactory = new ExamFactory();
            deliverable = deliverableFactory.createDeliverable(type, c);
        }
        if(d.equals("lecture")){
            deliverableFactory = new LectureFactory();
            deliverable = deliverableFactory.createDeliverable(type, c);
        }

        if(deliverable != null){
            deliverable.addObserver(this);
            deliverableRepository.save(deliverable);
            c.getDeliverables().add(deliverable);
            courseRepository.save(c);
        }

        return true;
    }

    public boolean gradeDeliverable(long sid, long did, double grade){

        if(!RestController.role.equals("professor")){

            return false;
        }


        Student s = studentRepository.findById(sid);
        Deliverable d = deliverableRepository.findById(did);

        StudentGrade sg = new StudentGrade(new GradeID(sid, did), grade, s, d);



        s.getDeliverableGrades().add(sg);
        d.getStudentGrades().add(sg);


        gradeRepository.save(sg);

        return true;
    }




    @Override
    public void update(Observable o, Object arg) {
        deliverableRepository.save((Deliverable) o);
    }
}
