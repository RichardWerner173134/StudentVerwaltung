package com.example.testDatabase.demotest.Repositories;

import com.example.testDatabase.demotest.Entities.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    List<Subject> findAll();
    Optional<Subject> findBySubjectName(String subjectName);
}
