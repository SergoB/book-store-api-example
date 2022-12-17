package com.coding.exercise.sicpa.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * An instance of the book for the rental shop
 */
@Data
@AllArgsConstructor
public class BookInstance {

    private Long bookId;
    private Book book;

}
