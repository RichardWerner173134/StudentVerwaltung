package com.example.testDatabase.demotest.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String vorname;

    private String nachname;

    private String city;

    private Long postalCode;

    private String street;

    private Long number;

    @ManyToMany (cascade=CascadeType.ALL)
    @JoinTable(
            name = "student_course_attendance",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> attendedCourses;

    public void addLikedCourses(Course course){
        attendedCourses.add(course);
        course.getAttendees().add(this);
    }

    public void removeLikedCourses(Course course){
        this.attendedCourses.remove(course);
        course.getAttendees().remove(this);
    }
}
