package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.Course;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.deliverables.Deliverable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {

    Deliverable findById(long did);

    Set<Deliverable> findDeliverablesByCourseIn(Set<Session> courses);


}
