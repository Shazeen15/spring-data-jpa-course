package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,
            args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student me = new Student("Shazeen",
                "Fabius",
                "shazeen@email.com",
                28);

            Student jamile = new Student("Jamile",
                "Fabius",
                "jamile@email.com",
                29);

            Student leslie = new Student("Leslie",
                "Fabius",
                "leslie@email.com",
                10);

            Student damien = new Student("Damien",
                "Fabius",
                "damien@email.com",
                10);

            studentRepository.saveAll(List.of(me,
                jamile,
                leslie,
                damien));

            studentRepository.findStudentByEmail("shazeen@email.com")
                .ifPresentOrElse(student -> {
                        System.out.println(student);
                    },
                    () -> {
                        System.out.println("Student with email shazeen@email.com not found.");
                    });

            studentRepository.findStudentByEmail("shazzy@email.com")
                .ifPresentOrElse(student -> {
                        System.out.println(student);
                    },
                    () -> {
                        System.out.println("Student with email shazzy@email.com not found.");
                    });

            studentRepository.findStudentsByFirstNameEqualsAndAgeEquals("Shazeen",
                    28)
                .forEach(student -> {
                    System.out.println(student);
                });

            studentRepository.findStudentsByFirstNameEqualsAndAgeEqualsNative("Shazeen",
                    28)
                .forEach(student -> {
                    System.out.println(student);
                });
        };
    }

}
