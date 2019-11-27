package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Enricher.CourseEnricher;
import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Entities.Subject;
import com.example.testDatabase.demotest.Repositories.CourseRepository;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import com.example.testDatabase.demotest.Repositories.SubjectRepository;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@Builder
public class CourseService {

    @Autowired
    private CourseEnricher courseEnricher;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public List<Course> getSignableCourses(Long studentId) throws Exception {
        List<Subject> subjects = subjectRepository.findAll();
        if(!studentRepository.findById(studentId).isPresent()){
            throw new Exception("Student with id=" + studentId + "not found");
        }
        Student studentDTO = studentRepository.findById(studentId).get();
        List<Course> attendedCourses = new ArrayList<>(studentDTO.getAttendedCourses());

        for(int i = 0; i < attendedCourses.size(); i++){
            if(subjects.contains(attendedCourses.get(i).getSubject())){
                subjects.remove(attendedCourses.get(i).getSubject());
            }
         }
        return courseRepository.findBySubjectIn(subjects);
    }

    public void addCourse(String courseSubject) throws Exception {
        Optional<Subject> subject = subjectRepository.findBySubjectName(courseSubject);
        if (subject.isPresent()) {
            int courseNr = courseEnricher.generateCourseNr(subject.get().getSubjectName());
            courseRepository.save(Course.builder()
                    .subject(subject.get())
                    .courseNr(courseNr)
                    .name(courseEnricher.generateCourseName(subject.get().getSubjectName(), courseNr))
                    .build());
        }else{
            throw new Exception("Subject with name=" + courseSubject + " not found");
        }
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }

    public Optional<Course> getCourse(Long id){
        return courseRepository.findById(id);
    }

    public List<Student> getCourseMembers(Long id) throws Exception {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return new ArrayList<>(course.get().getAttendees());
        }
        throw new Exception("CourseId not found");
    }

}
