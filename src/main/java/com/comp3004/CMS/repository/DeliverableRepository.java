package com.comp3004.CMS.repository;

import com.comp3004.CMS.base.deliverables.Deliverable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
}
