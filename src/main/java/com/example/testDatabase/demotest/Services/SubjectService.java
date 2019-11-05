package com.example.testDatabase.demotest.Services;

import com.example.testDatabase.demotest.Entities.Subject;
import com.example.testDatabase.demotest.Repositories.SubjectRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubject(String id){
        return subjectRepository.findById(id);
    }

    public void addSubject(String subjectName) throws Exception {
        subjectRepository.save(new Subject(subjectName));
    }

    public Optional<Subject> getSubjectForSubjectName(String subjectName){
        return subjectRepository.findBySubjectName(subjectName);
    }
}
