package com.comp3004.CMS.visitor;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;

public class Recognize implements Visitor{



    @Override
    public String visit(Student s) {
        return "student";
    }

    @Override
    public String visit(Professor p) {
        return "professor";
    }

    @Override
    public String visit(Session c) {
        return "session";
    }
}
