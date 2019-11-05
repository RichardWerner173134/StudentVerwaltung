package com.example.testDatabase.demotest.Entities;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany (cascade=CascadeType.ALL)
    @JoinTable(
            name = "student_course_attendance",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> attendedCourses;

    public Student(String name) throws Exception {
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
