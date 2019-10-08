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
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            student.get().addLikedCourses(courseRepository.findById(courseId).get());
            studentRepository.save(student.get());
        }
    }

    public void deleteCourseFromStudent(String studentId, String courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            student.get().removeLikedCourses(courseRepository.findById(courseId).get());
            studentRepository.save(student.get());
        }
    }

    public Iterable<Course> getCoursesForStudent(String studentId){
        return studentRepository.findById(studentId).get().getAttendedCourses();
    }
}
