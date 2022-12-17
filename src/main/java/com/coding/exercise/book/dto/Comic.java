package com.coding.exercise.sicpa.book.dto;

import java.util.List;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Comic extends Book {

    private List<Illustrator> illustrators;

    @Override
    public String toString() {
        return super.toString().concat( " and illustrated by " + illustrators);
    }
    
}
