package com.comp3004.CMS.services;

import com.comp3004.CMS.base.StudentGrade;
import com.comp3004.CMS.repository.DeliverableRepository;
import com.comp3004.CMS.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    DeliverableRepository deliverableRepository;

    public List<StudentGrade> findAll(){ return gradeRepository.findAll(); }

}
