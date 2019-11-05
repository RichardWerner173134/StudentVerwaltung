package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Subject;
import com.example.testDatabase.demotest.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    private String getAllSubjects(Model model){
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjectlist";
    }

    @GetMapping("/{id}")
    private String getSubject(@PathVariable String id, Model model) throws Exception {
        Optional<Subject> subject = subjectService.getSubject(id);
        if(subject.isPresent()){
            model.addAttribute("subjects", Arrays.asList(subject.get()));
        }else{
            throw new Exception("Subject with id=" + id + " not found");
        }
        return "studentlist";
    }

    @PostMapping(value="", consumes= MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void addSubject(@RequestBody String subjectName) throws Exception {
        subjectService.addSubject(subjectName);
    }

    @GetMapping("/addSubjectForm")
    private String addSubjectForm(){
        return "addSubjectForm";
    }
}
