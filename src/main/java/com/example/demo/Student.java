package com.example.demo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

}
