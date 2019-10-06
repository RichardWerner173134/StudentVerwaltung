package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    private Iterable<Student> getAllStudents (){
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    private void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping("/students/{id}")
    private void deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
    }
}
