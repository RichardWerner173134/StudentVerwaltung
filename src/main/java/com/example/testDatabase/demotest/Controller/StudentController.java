package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/home/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    private Iterable<Student> getAllStudents (){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    private Optional<Student> getStudent(@PathVariable String id){
        return studentService.getStudent(id);
    }

    @PostMapping("")
    private void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    private void deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
    }

    @PutMapping("/{studentId}/courses/{courseId}")
    private void updateStudent(@PathVariable String studentId, @PathVariable String courseId){
        studentService.addCourseToStudent(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    private void deleteCourseFromStudent(@PathVariable String studentId, @PathVariable String courseId){
        studentService.deleteCourseFromStudent(studentId, courseId);
    }
}
