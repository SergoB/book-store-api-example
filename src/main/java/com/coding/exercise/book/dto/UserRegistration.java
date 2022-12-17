package com.coding.exercise.sicpa.book.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegistration {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
