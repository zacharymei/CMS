package com.comp3004.CMS.services;


import com.comp3004.CMS.base.Professor;
import com.comp3004.CMS.base.Session;
import com.comp3004.CMS.base.Student;
import com.comp3004.CMS.base.User;
import com.comp3004.CMS.repository.CourseRepository;
import com.comp3004.CMS.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

@Service
@Transactional
public class ProfessorService implements Observer {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AdminService adminService;

    public List<Professor> findAll(){
        return (List<Professor>) professorRepository.findAll();
    }

    public Professor findById(long id){
        return professorRepository.findById(id);
    }

    public Professor getProfessorByUsername(String username) {return professorRepository.findProfessorByUsername(username); }

    public boolean addProfessor(String firstname, String lastname, String program, String password){

        if(!adminService.loggedIn()){
            return false;
        }

        Professor p = new Professor();
        p.setFirstName(firstname);
        p.setLastName(lastname);
        p.setProgram(program);

        String username = firstname.toLowerCase()+lastname.toLowerCase();
        if(professorRepository.countProfessorsByUsernameContains(username) > 0){
            username = username + Integer.toString(professorRepository.countProfessorsByUsernameContains(username)+1);

        }

        ((User)p).setUsername(username);
        ((User)p).setPassword(password);

        p.addObserver(this);
        professorRepository.save(p);

        return true;
    }

    public boolean assign(long professor_id, long cid){

        if(!adminService.loggedIn()){
            return false;
        }

        Session c = courseRepository.findById(cid);
        Professor p = professorRepository.findById(professor_id);
        c.setProfessor(p);

        return true;
    }

    public boolean login(String username, String password){
        if(professorRepository.findProfessorByUsername(username) != null){
            Professor p = professorRepository.findProfessorByUsername(username);
            if(p.getPassword().equals(password)){
                p.login();
                return true;
            }
        }
        return false;
    }

    //check what courses professor registered
    public Set<Session> professorRegistered(long id){
        Professor p = findById(id);
        return p.getCourses();
    }

    @Override
    public void update(Observable o, Object arg) {
        professorRepository.save((Professor) o);
    }
}
