package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Repositories.CourseRepository;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Service
@Builder
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void deleteCourse(String id){
        courseRepository.deleteById(id);
    }

    public Optional<Course> getCourse(String id){
        return courseRepository.findById(id);
    }

    public Iterable<Student> getCourseMembers(String id) throws Exception {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get().getAttendees();
        }
        throw new Exception("CourseId not found");
    }

}
