package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName = ?1 and s.age = ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(
        String firstName,
        Integer age);

    //    @Query(value = "select * from student where first_name = ?1 and age = ?2",
    //        nativeQuery = true)
    //    List<Student> findStudentsByFirstNameEqualsAndAgeEqualsNative(
    //        String firstName,
    //        Integer age);

    @Query(value = "select * from student where first_name = :firstName and age = :age",
        nativeQuery = true)
    List<Student> findStudentsByFirstNameEqualsAndAgeEqualsNative(
        @Param("firstName")
            String firstName,
        @Param("age")
            Integer age);
}
