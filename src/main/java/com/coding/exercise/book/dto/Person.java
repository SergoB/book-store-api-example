package com.coding.exercise.sicpa.book.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Person {
    
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
