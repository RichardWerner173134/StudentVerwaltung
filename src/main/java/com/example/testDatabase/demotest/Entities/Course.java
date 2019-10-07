package com.example.testDatabase.demotest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "likedCourses")
    @JsonIgnore
    private Set<Student> likes;
}
