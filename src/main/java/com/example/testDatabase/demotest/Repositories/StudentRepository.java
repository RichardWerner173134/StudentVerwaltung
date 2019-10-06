package com.example.testDatabase.demotest.Repositories;

import com.example.testDatabase.demotest.Entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
}
