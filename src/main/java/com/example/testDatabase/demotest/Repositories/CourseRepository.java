package com.example.testDatabase.demotest.Repositories;

import com.example.testDatabase.demotest.Entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
}
