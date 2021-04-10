package com.comp3004.CMS.visitor;

import com.comp3004.CMS.base.*;

import java.util.Observable;

public interface Visitor {

    void acceptUpdate(Student s, Observable o, Object arg);
    void acceptUpdate(Professor p, Observable o, Object arg);
    void acceptUpdate(Session c, Observable o, Object arg);

}
