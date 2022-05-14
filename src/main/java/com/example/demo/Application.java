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

            studentRepository.saveAll(List.of(me,
                jamile));

            System.out.println("Number of Students: ");
            System.out.println(studentRepository.count());

            studentRepository.findById(2L)
                .ifPresentOrElse(student -> {
                        System.out.println(student);
                    },
                    () -> {
                        System.out.println("Student with id 2 not found");
                    });

            studentRepository.findById(3L)
                .ifPresentOrElse(student -> {
                        System.out.println(student);
                    },
                    () -> {
                        System.out.println("Student with id 3 not found");
                    });

            System.out.println("Adding students");
            List<Student> students = studentRepository.findAll();

            System.out.println("Printing all students");
            students.forEach(student -> {
                System.out.println(student);
            });
            System.out.println(students);

            System.out.println("Deleting with student Id 1L");
            studentRepository.deleteById(1L);

            System.out.println("Number of Students: ");
            System.out.println(studentRepository.count());

            //studentRepository.deleteAll();
        };
    }

}
