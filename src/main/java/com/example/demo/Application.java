package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,
            args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            generateRandomStudent(studentRepository);


        };
    }

    private void sorting(StudentRepository studentRepository) {
        Sort sortedByFirstName = Sort.by("firstName")
            .ascending()
            .and(Sort.by("age")
                .descending());

        studentRepository.findAll(sortedByFirstName)
            .forEach(student -> {
                System.out.println(student.getFirstName() + " -- " + student.getAge());
            });
    }

    private void generateRandomStudent(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i <= 1000; i++) {
            String firstName = faker.name()
                .firstName();
            String lastName = faker.name()
                .lastName();
            String email = String.format("%s.%s@email.com",
                firstName,
                lastName);

            Student student = new Student(firstName,
                lastName,
                email,
                faker.number()
                    .numberBetween(0,
                        100));

            studentRepository.save(student);
        }
    }

}
