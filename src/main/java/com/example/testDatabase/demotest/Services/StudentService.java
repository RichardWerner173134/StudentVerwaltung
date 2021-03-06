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

    public void addStudent(String vorname, String nachname, String city, Long postalCode, String street, Long number){
        studentRepository.save(Student.builder()
                                .vorname(vorname)
                                .nachname(nachname)
                                .city(city)
                                .postalCode(postalCode)
                                .street(street)
                                .number(number)
                                .build());
    }

    public void updateStudentAttributes(Long studentId, Student student){
        Optional<Student> studentDTO = studentRepository.findById(studentId);
        if(studentDTO.isPresent()){
            studentDTO.get().setCity(student.getCity());
            studentDTO.get().setNachname(student.getNachname());
            studentDTO.get().setVorname(student.getVorname());
            studentDTO.get().setStreet(student.getStreet());
            studentDTO.get().setNumber(student.getNumber());
            studentDTO.get().setPostalCode(student.getPostalCode());
            studentRepository.save(studentDTO.get());
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
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

    public void deleteCourseFromStudent(Long studentId, Long courseId) {
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
