package com.comp3004.CMS.visitor;

import com.comp3004.CMS.base.*;

import java.util.Observable;

public interface Visitor {

    String visit(Student s);
    String visit(Professor p);
    String visit(Session c);


}
