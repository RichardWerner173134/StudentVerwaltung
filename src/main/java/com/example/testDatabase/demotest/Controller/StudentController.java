package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.JsonRequest.CourseForStudentJson;
import com.example.testDatabase.demotest.JsonRequest.StudentJson;
import com.example.testDatabase.demotest.Services.CourseService;
import com.example.testDatabase.demotest.Services.StudentService;
import com.example.testDatabase.demotest.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    private String getAllStudents(Model model){
        model.addAttribute("header", "showAllStudents");
        model.addAttribute("students", studentService.getAllStudents());
        return "studentlist";
    }

    @GetMapping("/{id}/showStudentAttributes")
    private String getStudent(@PathVariable String id, Model model) throws Exception {
        Optional<Student> student = studentService.getStudent(Long.parseLong(id));
        if(student.isPresent()){
            model.addAttribute("student", student.get());
        }else{
            throw new Exception("Student with id=" + id + " not found");
        }
        return "showStudentAttributes";
    }

    @PostMapping(value="", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void addStudent(@RequestBody StudentJson studentJson) throws Exception {
        studentService.addStudent(studentJson.getVorname(),
                studentJson.getNachname(),
                studentJson.getCity(),
                Long.parseLong(studentJson.getPostalCode()),
                studentJson.getStreet(),
                Long.parseLong(studentJson.getNumber()));
    }

    @GetMapping("/deleteStudentForm")
    private String deleteStudentForm(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "deleteStudentForm";
    }

    @PutMapping(value="/{studentId}/courses/{courseId}", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void addCourseToStudent(@RequestBody CourseForStudentJson courseForStudentJson){
        studentService.addCourseToStudent(Long.parseLong(courseForStudentJson.getStudentId()), Long.parseLong(courseForStudentJson.getCourseId()));
    }

    @PutMapping(value="/{studentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void updateStudentProperties(@PathVariable String studentId, @RequestBody Student student){
        studentService.updateStudentAttributes(Long.parseLong(studentId), student);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    private void deleteCourseFromStudent(@PathVariable String studentId, @PathVariable String courseId){
        studentService.deleteCourseFromStudent(Long.parseLong(studentId), Long.parseLong(courseId));
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

    @GetMapping("/{studentId}/courses/addCourseForStudent")
    private String addCourseForStudent (@PathVariable String studentId, Model model) throws Exception {
        Optional<Student> student = studentService.getStudent(Long.parseLong(studentId));
        if(student.isPresent()){
            model.addAttribute("student", student.get());
        }else{
            throw new Exception("Student with id " + studentId + " not found");
        }
        List<Course> courses = courseService.getSignableCourses(Long.parseLong(studentId));
        model.addAttribute("courses", courses);
        return "addCourseForStudent";
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteStudent(@PathVariable String studentId){
        List<Course> studentCourses = studentService.getCoursesForStudent(Long.parseLong(studentId));
        for(Course courseToRemove: studentCourses){
            studentService.deleteCourseFromStudent(Long.parseLong(studentId), courseToRemove.getId());
        }
        studentService.deleteStudent(Long.parseLong(studentId));
    }
}
