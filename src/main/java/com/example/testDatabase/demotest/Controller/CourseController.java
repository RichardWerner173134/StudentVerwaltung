package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Services.CourseService;
import com.example.testDatabase.demotest.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    private String getAllCourses(Model model){
        model.addAttribute("header", "showAllCourses");
        model.addAttribute("courses", courseService.getCourses());
        return "courselist";
    }

    @GetMapping("/{id}")
    private String getCourse(@PathVariable String id, Model model) throws Exception {
        Optional<Course> course = courseService.getCourse(Long.parseLong(id));
        if(course.isPresent()) {
            model.addAttribute("header","showCourse");
            model.addAttribute("courses", Arrays.asList(course.get()));
        }else{
            throw new Exception("Course with id=" + id + " not found");
        }
        return "courselist";
    }

    @PostMapping(value="", consumes= MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void addCourse(@RequestBody String courseSubject) throws Exception {
        courseService.addCourse(courseSubject);
    }

    @DeleteMapping("/{id}")
    private void deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
    }

    @GetMapping("/{courseId}/students")
    private List<Student> getCourseMembers(@PathVariable String courseId) throws Exception {
        return courseService.getCourseMembers(courseId);
    }

    @GetMapping("/addCourseForm")
    private String addCourseForm(Model model){
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "addCourseForm";
    }
}
