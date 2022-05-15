package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,
            args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
        StudentRepository studentRepository,
        StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Faker faker = new Faker();
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
            StudentIdCard studentIdCard = new StudentIdCard("123456789",
                student);

            String courseName = faker.buffy()
                .characters();
            String departmentName = faker.music()
                .genre();

            Course course1 = new Course(courseName,
                departmentName);

            String courseName1 = faker.buffy()
                .characters();
            String departmentName1 = faker.music()
                .genre();

            Course course = new Course(courseName1,
                departmentName1);

            student.enrollToCourse(course);
            student.enrollToCourse(course1);

            String bookName = faker.book()
                .title();
            LocalDateTime createdAt = LocalDateTime.now()
                .minusDays(10);

            Book book = new Book(bookName,
                createdAt);

            student.addBook(book);

            String bookName2 = faker.book()
                .title();

            LocalDateTime createdAt2 = LocalDateTime.now()
                .minusDays(15);

            Book book2 = new Book(bookName2,
                createdAt2);

            student.addBook(book2);

            String bookName3 = faker.book()
                .title();

            LocalDateTime createdAt3 = LocalDateTime.now()
                .minusDays(20);

            Book book3 = new Book(bookName3,
                createdAt3);

            student.addBook(book3);

            student.setStudentIdCard(studentIdCard);

            studentRepository.save(student);

            studentRepository.findById(1L)
                .ifPresent(s -> {
                    // fetch = FetchType.EAGER
                    //                    List<Book> sbooks = s.getBooks();
                    //                    sbooks.forEach(bk -> {
                    //                        System.out.println(bk.getBookName());
                    //                    });

                    // fetch = FetchType.LAZY
                    System.out.println("fetch book lazy...");
                    List<Book> books = student.getBooks();
                    books.forEach(b -> {
                        System.out.println(s.getFirstName() + " borrowed " + b.getBookName());
                    });
                });

            //            studentIdCardRepository.findById(1L)
            //                .ifPresent(System.out::println);

            //            studentRepository.deleteById(1L);
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
