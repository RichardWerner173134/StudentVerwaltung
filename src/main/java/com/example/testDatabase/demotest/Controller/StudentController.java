package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.JsonRequest.StudentJson;
import com.example.testDatabase.demotest.Services.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    private String getAllStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "studentlist";
    }

    @GetMapping("/{id}")
    private String getStudent(@PathVariable String id, Model model) throws Exception {
        Optional<Student> student = studentService.getStudent(Long.parseLong(id));
        if(student.isPresent()){
            model.addAttribute("students", Arrays.asList(student.get()));
        }else{
            throw new Exception("Student with id=" + id + " not found");
        }
        return "studentlist";
    }

    @PostMapping(value="", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void addStudent(@RequestBody StudentJson studentJson) throws Exception {
        studentService.addStudent(studentJson.getVorname(), studentJson.getNachname());
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

    @GetMapping("/{studentId}/courses")
    private String getCoursesForStudent(@PathVariable String studentId, Model model){
        model.addAttribute("header", "showCoursesForStudent");
        model.addAttribute("student", studentService.getStudent(Long.parseLong(studentId)).get());
        model.addAttribute("courses", studentService.getCoursesForStudent(Long.parseLong(studentId)));
        return "courselist";
    }

    @GetMapping("/addStudentForm")
    private String addStudentForm(){
        return "addStudentForm";
    }
}
