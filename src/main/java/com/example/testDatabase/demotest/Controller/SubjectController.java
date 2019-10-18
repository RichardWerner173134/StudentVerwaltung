package com.example.testDatabase.demotest.Controller;

import com.example.testDatabase.demotest.Entities.Subject;
import com.example.testDatabase.demotest.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    private Iterable<Subject> getSubjects(){
        return subjectService.getAllSubjects();
    }

    @PostMapping("")
    private void addSubject(@RequestBody Subject subject){
        subjectService.addSubject(subject);
    }
}
