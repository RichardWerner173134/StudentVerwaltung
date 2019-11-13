package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Repositories.CourseRepository;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public void addStudent(String vorname, String nachname){
        studentRepository.save(Student.builder()
                                .vorname(vorname)
                                .nachname(nachname)
                                .build());
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public void addCourseToStudent(Long studentId, Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student studentDTO = student.get();
            studentDTO.addLikedCourses(courseRepository.findById(courseId).get());
            studentRepository.save(studentDTO);
        }
    }

    public void deleteCourseFromStudent(String studentId, String courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student studentDTO = student.get();
            studentDTO.removeLikedCourses(courseRepository.findById(courseId).get());
            studentRepository.save(studentDTO);
        }
    }

    public List<Course> getCoursesForStudent(Long studentId) {
        return new ArrayList<>(studentRepository.findById(studentId).get().getAttendedCourses());
    }
}
