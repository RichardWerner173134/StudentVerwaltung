package com.example.testDatabase.demotest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private int courseNr;

    private String name;

    @ManyToMany(mappedBy = "attendedCourses")
    @JsonIgnore
    private Set<Student> attendees;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
}
