package com.example.testDatabase.demotest.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "likedCourses")
    private Set<Student> likes;
}
