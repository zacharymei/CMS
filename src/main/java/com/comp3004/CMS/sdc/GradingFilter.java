package com.comp3004.CMS.sdc;

import com.comp3004.CMS.base.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface GradingFilter {

    int sample();

    Set<GradePoint> inProgram(String program);

}
