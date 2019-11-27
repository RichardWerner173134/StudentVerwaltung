package com.example.testDatabase.demotest.Repositories;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Entities.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findBySubjectIn(List<Subject> subjects);
}
