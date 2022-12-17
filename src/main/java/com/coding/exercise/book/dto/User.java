package com.coding.exercise.sicpa.book.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
public class User extends Person {
    // Some specific user characteristics
    Long userId;
}
