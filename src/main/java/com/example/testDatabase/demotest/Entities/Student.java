package com.example.testDatabase.demotest.Entities;

import com.example.testDatabase.demotest.Enricher.StudentEnricher;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Autowired
    @Transient
    private StudentEnricher se = StudentEnricher.builder().build();

    @Id
    @GeneratedValue
    private String id;

    private String name;

    @ManyToMany (cascade=CascadeType.ALL)
    @JoinTable(
            name = "student_course_attendance",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> attendedCourses;

    public Student(String name, StudentRepository studentRepository) throws Exception {
        this.id = se.generateId(studentRepository);
        this.name = name;
    }

    public void addLikedCourses(Course course){
        attendedCourses.add(course);
        course.getAttendees().add(this);
    }

    public void removeLikedCourses(Course course){
        this.attendedCourses.remove(course);
        course.getAttendees().remove(this);
    }
}
