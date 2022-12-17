package com.coding.exercise.sicpa.book.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exercise.sicpa.book.dao.MockedBookRentalDao;
import com.coding.exercise.sicpa.book.dto.Book;
import com.coding.exercise.sicpa.book.dto.BookInstance;
import com.coding.exercise.sicpa.book.dto.BookRental;
import com.coding.exercise.sicpa.book.dto.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookRentalShopService {

    @Autowired
    private MockedBookRentalDao mockedBookRentalDao;

    public Map<Book, Long> getBookInstances() {
        return mockedBookRentalDao.getBookInstances().stream().collect(Collectors.groupingBy(BookInstance::getBook, Collectors.counting()));
    }

    public List<BookInstance> getBooksAvailable() {
        return mockedBookRentalDao.getBookInstances().stream()
        .filter(bookInstance -> !isBookRented(bookInstance.getBookId()))
        .toList();
    }

    public void registerUser(String firstName, String lastName, LocalDate birthDate) {
        mockedBookRentalDao.createUser(User.builder().firstName(firstName).lastName(lastName).birthDate(birthDate).build());
    }

    public List<User> getUsers() {
        return mockedBookRentalDao.getUsers();
    }

    public void rentBook(Long bookId, Long userId) {
       if (isBookRented(bookId)){
        log.error("Book {} is already rented", bookId);
        throw new NotAllowedException("The book is already rented");
       }

        BookRental bookRental = new BookRental(
            mockedBookRentalDao.getBookInstanceById(bookId), 
            mockedBookRentalDao.getUserById(userId));
        mockedBookRentalDao.createBookRental(bookRental);
    }

    public BookRental returnBook(Long bookId) {
        BookRental bookToReturn = mockedBookRentalDao.getBookRentals().stream()
            .filter(bookRental -> bookRental.getBook().getBookId().equals(bookId))
            .findFirst()
            .orElseThrow(NotFoundException::new);

        bookToReturn.setReturnDate(LocalDate.now());
        return bookToReturn;
    }

    public void registerBook(BookInstance bookInstance) {
        mockedBookRentalDao.createBookInstance(bookInstance);
    } 

    private boolean isBookRented(Long bookId) {
        List<Long> booksRented = mockedBookRentalDao.getBookRentals().stream()
        .filter(bookRental -> Objects.isNull(bookRental.getReturnDate()))
        .map(bookRental -> bookRental.getBook().getBookId())
        .toList();
        return booksRented.contains(bookId);
    }
}
