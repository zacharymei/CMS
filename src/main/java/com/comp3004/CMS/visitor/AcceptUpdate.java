package com.comp3004.CMS.visitor;

import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;

import java.util.Observable;

public class AcceptUpdate implements Visitor{

    @Override
    public void acceptUpdate(Student s, Observable o, Object arg) {

    }

    @Override
    public void acceptUpdate(Professor p, Observable o, Object arg) {

    }

    @Override
    public void acceptUpdate(Session c, Observable o, Object arg) {

    }
}
