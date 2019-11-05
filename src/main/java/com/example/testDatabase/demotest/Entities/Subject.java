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
public class Subject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;

    public Subject(String subjectName) throws Exception {
        this.subjectName=subjectName;
    }
}
