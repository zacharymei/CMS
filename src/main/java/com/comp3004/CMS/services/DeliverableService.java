package com.comp3004.CMS.services;

import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Deliverable;
import com.comp3004.CMS.base.deliverables.factory.AssignmentFactory;
import com.comp3004.CMS.base.deliverables.factory.DeliverableFactory;
import com.comp3004.CMS.base.deliverables.factory.ExamFactory;
import com.comp3004.CMS.base.deliverables.factory.LectureFactory;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.repository.DeliverableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverableService {

    @Autowired
    DeliverableRepository deliverableRepository;
    @Autowired
    CourseRepository courseRepository;

    DeliverableFactory deliverableFactory;


    public boolean createDeliverable(long cid, String d, String type){
        if(d.equals("assignment")){
            Session c = courseRepository.findById(cid);
            deliverableFactory = new AssignmentFactory();
            Deliverable deliverable = deliverableFactory.createDeliverable(type, c);
            System.out.println(deliverable);
            deliverableRepository.save((Deliverable) deliverable);
            c.getDeliverables().add(deliverable);
            courseRepository.save(c);
        }
//        if(d.equals("exam")){
//            deliverableFactory = new ExamFactory();
//            deliverableFactory.createDeliverable(type, );
//        }
//        if(d.equals("lecture")){
//            deliverableFactory = new LectureFactory();
//            deliverableFactory.createDeliverable(type);
//        }
        return true;
    }

}
