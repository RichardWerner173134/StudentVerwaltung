package com.example.testDatabase.demotest.Entities;

import com.example.testDatabase.demotest.Enricher.CourseEnricher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    private String id;

    private int courseNr;

    private String name;

    @Transient
    @Autowired
    private CourseEnricher ce;

    @ManyToMany(mappedBy = "attendedCourses")
    @JsonIgnore
    private Set<Student> attendees;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    public Course(Subject subject) throws Exception {
        this.subject=subject;
        id=ce.generateCourseId(subject.getSubjectName());
        courseNr = ce.generateCourseNr(subject.getSubjectName());
        name = ce.generateCourseName(subject.getSubjectName(), courseNr);
    }
}
