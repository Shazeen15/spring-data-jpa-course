package com.example.demo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Book")
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @SequenceGenerator(name = "book_id_sequence",
        sequenceName = "book_id_sequence",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "book_id_sequence")
    @Column(name = "id",
        updatable = false)
    private Long id;

    @Column(name = "created_at",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @Column(name = "book_name",
        nullable = false)
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "student_id",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;

    public Book(
        LocalDateTime createdAt,
        String bookName,
        Student student) {
        this.createdAt = createdAt;
        this.bookName = bookName;
        this.student = student;
    }
}
