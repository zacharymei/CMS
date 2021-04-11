package com.comp3004.CMS.visitor;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;

import java.util.Observable;

public class LogInfo implements Visitor{


    @Override
    public String visit(Student s) {
        return " Student: " + s.getFirstName()+ " " + s.getLastName();
    }

    @Override
    public String visit(Professor p) {
        return " Professor: " + p.getFirstName()+ " " + p.getLastName();
    }

    @Override
    public String visit(Session c) {
        return " Course: " + c.getProgram() + c.getNumber() + c.getSession() + " " + c.getTerm();
    }
}
