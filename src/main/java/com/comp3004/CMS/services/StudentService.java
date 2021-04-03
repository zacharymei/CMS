package com.comp3004.CMS.services;

import com.comp3004.CMS.base.*;
import java.util.List;

import com.comp3004.CMS.base.User;
import com.comp3004.CMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

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
        ((User)s).setUsername(firstname+lastname);
        ((User)s).setPassword(password);

        studentRepository.save(s);

        return true;
    }

    public boolean register(long student_id, Course c){
        Student s = studentRepository.findById(student_id);
        s.registerCourse(c);
        studentRepository.save(s);
        return true;
    }

}
