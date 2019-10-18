package com.example.testDatabase.demotest.Entities;

import com.example.testDatabase.demotest.Enricher.SubjectEnricher;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    @GeneratedValue
    private String id;

    @Autowired
    private SubjectEnricher subjectEnricher = SubjectEnricher.builder().build();

    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;

    public Subject(String subjectName) throws Exception {
        this.id=subjectEnricher.generateSubjectId(subjectName);
        this.subjectName=subjectName;
    }
}
