package com.example.testDatabase.demotest.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String id;

    private String name;

    @ManyToMany (cascade=CascadeType.ALL)
    @JoinTable(
            name = "course_like",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> likedCourses;

    public void addLikedCourses(Course course){
        likedCourses.add(course);
        course.getLikes().add(this);
    }

    public void removeLikedCourses(Course course){
        this.likedCourses.remove(course);
        course.getLikes().remove(this);
    }
}
