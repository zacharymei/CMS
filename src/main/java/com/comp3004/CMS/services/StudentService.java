package com.comp3004.CMS.services;

import com.comp3004.CMS.base.*;
import java.util.List;
import java.util.*;
import java.util.Set;

import com.comp3004.CMS.base.User;
import com.comp3004.CMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService{

    @Autowired
    private StudentRepository studentRepository;



    public List<Student> findAll(){
        return (List<Student>) studentRepository.findAll();
    }

    public Student findById(long id){
        return studentRepository.findById(id);
    }

    public boolean addStudent(String firstname, String lastname, String program, String password){
        Student s = new Student();
        s.setFirstName(firstname);
        s.setLastName(lastname);
        s.setProgram(program);

        String username = firstname.toLowerCase()+lastname.toLowerCase();
        if(studentRepository.countStudentByUsernameContains(username) > 0){
            username = username + Integer.toString(studentRepository.countStudentByUsernameContains(username)+1);

        }

        ((User)s).setUsername(username);
        ((User)s).setPassword(password);

        studentRepository.save(s);

        return true;
    }

    public boolean deleteStudent(long student_id){
        if(studentRepository.findById(student_id) != null){
            Student s = studentRepository.findById(student_id);
            s.delete();
            studentRepository.delete(s);
            return true;
        }
        return false;
    }

    public boolean register(long student_id, Session c){
        Student s = studentRepository.findById(student_id);
        s.registerCourse(c);
        studentRepository.save(s);
        return true;
    }

    public boolean drop(long sid, Session c){
        Student s = studentRepository.findById(sid);
        s.dropCourse(c);
        studentRepository.save(s);
        return true;
    }

    public Set<Session> studentRegistered(long id){
        Student s = findById(id);
        return s.getCourses();
    }

    public boolean login(String username, String password){
        if(studentRepository.findStudentByUsernameEquals(username) != null){
            Student s = studentRepository.findStudentByUsernameEquals(username);
            if(s.getPassword().equals(password)){
                s.login();
                studentRepository.save(s);
                return true;
            }
        }
        return false;
    }

    public boolean logout(String username){
        if(studentRepository.findStudentByUsernameEquals(username) != null){
            Student s = studentRepository.findStudentByUsernameEquals(username);
            s.logout();
            studentRepository.save(s);
            return true;
        }
        return false;
    }

}
