package com.coding.exercise.sicpa.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.sicpa.book.dto.Book;
import com.coding.exercise.sicpa.book.dto.BookInstance;
import com.coding.exercise.sicpa.book.dto.BookRental;
import com.coding.exercise.sicpa.book.dto.User;
import com.coding.exercise.sicpa.book.dto.UserRegistration;
import com.coding.exercise.sicpa.book.service.BookRentalShopService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("book-rental/api/v1/")
public class BookRentalShopController {
    
    @Autowired
    private BookRentalShopService bookRentalShopService;

    @GetMapping("book")
    @Operation(summary = "Return the list of books registered in the rental shop, with their available quantities (Including those currently rented)")
    public Map<Book, Long> getBooksRegistered() {
        return bookRentalShopService.getBookInstances();
    }

    @PostMapping("book")
    @Operation(summary = "Register a new book instance in the shop (Inital book json must be provided in payload)")
    public void registerBook(@RequestBody BookInstance bookInstance) {
        bookRentalShopService.registerBook(bookInstance);;
    }

    @GetMapping("book/available")
    @Operation(summary = "Return the list of books available for renting")
    public List<BookInstance> getAvailableBooks() {
        return bookRentalShopService.getBooksAvailable();
    }

    @PostMapping("book/{bookId}/rent")
    @Operation(summary = "Perform the operation allowing a user to rent a book")
    public void rentBook(@PathVariable Long bookId, @RequestParam Long userId) {
        bookRentalShopService.rentBook(bookId, userId);
    }

    @PutMapping("book/{bookId}/return")
    @Operation(summary = "Perform the operation allowing a user who rented a book, to return it")
    public BookRental returnBook(@PathVariable Long bookId) {
        return bookRentalShopService.returnBook(bookId);
    }

    
    @GetMapping("user")
    @Operation(summary = "Return the list of users who can rent a book in the store")
    public List<User> getUsers() {
        return bookRentalShopService.getUsers();
    }

    @PostMapping("user")
    @Operation(summary = "Register a new user")
    public void registerUser(@RequestBody UserRegistration userRegistration) {
        bookRentalShopService.registerUser(
            userRegistration.getFirstName(), 
            userRegistration.getLastName(), 
            userRegistration.getBirthDate()
            );
    }
}
