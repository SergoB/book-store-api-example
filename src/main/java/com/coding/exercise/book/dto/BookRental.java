package com.coding.exercise.sicpa.book.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookRental {

    private static final int DEFAULT_RENT_MONTHS_DURATION = 2;

    public BookRental(BookInstance bookInstance, User user) {
        this.book = bookInstance;
        this.user = user;
        this.rentDate = LocalDate.now();
        this.expectedReturnDate = rentDate.plusMonths(DEFAULT_RENT_MONTHS_DURATION);
    }

    private BookInstance book;

    private User user;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private LocalDate expectedReturnDate;

}
