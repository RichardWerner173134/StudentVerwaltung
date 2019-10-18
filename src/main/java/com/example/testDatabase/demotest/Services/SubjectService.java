package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Entities.Subject;
import com.example.testDatabase.demotest.Repositories.SubjectRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Iterable<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public void addSubject(Subject subject){
        subjectRepository.save(subject);
    }
}
