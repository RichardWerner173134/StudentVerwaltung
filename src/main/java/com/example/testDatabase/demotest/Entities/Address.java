package com.example.testDatabase.demotest.Entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String street;
    @Column(nullable=false)
    private Long houseNumber;
    @Column(nullable=false)
    private Long postalCode;
    @Column(nullable=true)
    private char suffix;

    @Column(nullable=false)
    private String city;

    @OneToMany(mappedBy="address")
    private Set<Student> students;
}
