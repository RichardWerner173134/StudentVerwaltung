package com.example.testDatabase.demotest.Entities;

import com.example.testDatabase.demotest.Enricher.SubjectEnricher;
import com.example.testDatabase.demotest.Repositories.SubjectRepository;
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
    @Transient
    @Autowired
    private SubjectEnricher subjectEnricher = SubjectEnricher.builder().build();

    @Id
    @GeneratedValue
    private String id;

    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;

    public Subject(String subjectName, SubjectRepository subjectRepository) throws Exception {
        this.id=subjectEnricher.generateSubjectId(subjectRepository);
        this.subjectName=subjectName;
    }
}
