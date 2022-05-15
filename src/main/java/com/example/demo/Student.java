package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Student")
@Table(name = "student",
    uniqueConstraints = {@UniqueConstraint(name = "student_email_unique",
        columnNames = "email")})
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "student_sequence")
    @Column(name = "id",
        updatable = false)
    private Long id;

    @Column(name = "first_name",
        nullable = false,
        columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name",
        nullable = false,
        columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "email",
        nullable = false,
        columnDefinition = "TEXT")
    private String email;

    @Column(name = "age",
        nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "student",
        orphanRemoval = true)
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Book> books = new ArrayList<>();

    public Student(
        String firstName,
        String lastName,
        String email,
        Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student(
        String firstName,
        String lastName,
        String email,
        Integer age,
        StudentIdCard studentIdCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.studentIdCard = studentIdCard;
    }

    public void addBook(Book book) throws IllegalAccessException {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        } else {
            throw new IllegalAccessException("Student already has the book.");
        }
    }

    public void removeBook(Book book) throws IllegalAccessException {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        } else {
            throw new IllegalAccessException("Student does not have the book.");
        }
    }

    @Override
    public String toString() {
        return "Student{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", age=" + age + '}';
    }
}
