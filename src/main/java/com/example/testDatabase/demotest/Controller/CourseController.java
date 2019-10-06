package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    private Iterable<Course> getAllCourses(){
        return courseService.getCourses();
    }

    @GetMapping("/courses/{id}")
    private Optional<Course> getCourses(@PathVariable String id){
        return courseService.getCourse(id);
    }

    @PostMapping("/courses")
    private void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    @DeleteMapping("/courses/{id}")
    private void deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
    }
}
