package com.comp3004.CMS.sdc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompoundFilter implements GradingFilter{


    Set<GradingFilter> gradingFilters = new HashSet<>();


    public void add(GradingFilter filter){
        gradingFilters.add(filter);
    }

    public void remove(GradingFilter filter){
        gradingFilters.remove(filter);
    }

    public Set<GradingFilter> getChildren(){
        return gradingFilters;
    }

    @Override
    public int sample() {
        return 0;
    }

    @Override
    public Set<GradePoint> inProgram(String program) {

        Set<GradePoint> points = new HashSet<>();

        for(GradingFilter f : gradingFilters){
            points.addAll(f.inProgram(program));
        }

        return points;
    }
}
