package com.example.testDatabase.demotest.Repositories;

import com.example.testDatabase.demotest.Entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {
    List<Student> findAll();
    Optional<Student> findById(Long id);
}
