package com.example.demo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_sequence",
        sequenceName = "course_id_sequence",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "course_id_sequence")
    @Column(name = "id",
        updatable = false)
    private Long id;

    @Column(name = "course_name",
        nullable = false,
        columnDefinition = "TEXT")
    private String courseName;

    @Column(name = "department",
        nullable = false,
        columnDefinition = "TEXT")
    private String department;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course(
        String courseName,
        String department) {
        this.courseName = courseName;
        this.department = department;
    }
}
