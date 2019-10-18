package com.example.testDatabase.demotest.Repositories;

import com.example.testDatabase.demotest.Entities.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    List<Course> findAll();
}
