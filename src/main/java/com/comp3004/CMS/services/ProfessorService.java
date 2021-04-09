package com.comp3004.CMS.services;


import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.base.User;
import com.comp3004.CMS.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> findAll(){
        return (List<Professor>) professorRepository.findAll();
    }

    public Professor findById(long id){
        return professorRepository.findById(id);
    }

    public boolean addProfessor(String firstname, String lastname, String program, String password){
        Professor p = new Professor();
        p.setFirstName(firstname);
        p.setLastName(lastname);
        p.setProgram(program);
        ((User)p).setUsername(firstname+lastname);
        ((User)p).setPassword(password);

        professorRepository.save(p);

        return true;
    }

    public boolean assign(long professor_id, Session c){
        Professor p = professorRepository.findById(professor_id);
        p.assignCourse(c);
        professorRepository.save(p);
        return true;
    }

    //check what courses professor registered
    public Set<Session> professorRegistered(long id){
        Professor p = findById(id);
        return p.getCourses();
    }

}
