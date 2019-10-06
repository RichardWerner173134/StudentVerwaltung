package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudent(String id){
        return studentRepository.findById(id);
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudent(String id){
        studentRepository.deleteById(id);
    }
}
