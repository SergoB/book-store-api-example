package com.coding.exercise.sicpa.book.dto;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class Book {

    private String title;

    private Category category;

    private List<Author> authors;

    @Override
    public String toString() {
        return title + " is a " + category + " book, written by " + authors;
    }

}
