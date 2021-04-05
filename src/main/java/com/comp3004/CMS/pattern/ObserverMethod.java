package com.comp3004.CMS.pattern;

import com.comp3004.CMS.base.*;
import java.util.Observable;
import java.util.Observer;

public interface ObserverMethod extends Observer {


    void update(Observable o, Course arg);
}
