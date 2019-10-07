package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Repositories.CourseRepository;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

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

    public void addCourseToStudent(String studentId, String courseId){
        Student student = studentRepository.findById(studentId).get();
        student.addLikedCourses(courseRepository.findById(courseId).get());
        studentRepository.save(student);
    }

    public void deleteCourseFromStudent(String studentId, String courseId){
        Optional<Student> student = studentRepository.findById(studentId);
        student.get().getLikedCourses().remove(courseRepository.findById(courseId).get());
        studentRepository.save(student.get());
    }
}
