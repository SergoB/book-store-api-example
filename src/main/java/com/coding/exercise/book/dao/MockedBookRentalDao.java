package com.coding.exercise.sicpa.book.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Component;

import com.coding.exercise.sicpa.book.dto.Author;
import com.coding.exercise.sicpa.book.dto.Book;
import com.coding.exercise.sicpa.book.dto.BookInstance;
import com.coding.exercise.sicpa.book.dto.BookRental;
import com.coding.exercise.sicpa.book.dto.Category;
import com.coding.exercise.sicpa.book.dto.Comic;
import com.coding.exercise.sicpa.book.dto.Illustrator;
import com.coding.exercise.sicpa.book.dto.Novel;
import com.coding.exercise.sicpa.book.dto.User;

@Component
public class MockedBookRentalDao {

    private Long BOOK_SEQ = 10L;
    private Long USER_SEQ = 10L;

    private static final Author AUTHOR1 = Author.builder().firstName("Herbert George").lastName("Wells").birthDate(LocalDate.of(1866, 9, 21)).build();
    private static final Author AUTHOR2 = Author.builder().firstName("Albert").lastName("Uderzo").birthDate(LocalDate.of(1927, 12, 12)).build();
    private static final Author AUTHOR3 = Author.builder().firstName("René").lastName("Goscinny").birthDate(LocalDate.of(1926, 10, 14)).build();
    private static final Illustrator ILLUSTRATOR1 = Illustrator.builder().firstName("René").lastName("Goscinny").birthDate(LocalDate.of(1926, 10, 14)).build();

    private static final Book NOVEL1 = Novel.builder().title("The Time Machine").authors(List.of(AUTHOR1)).category(Category.SCIFI).build();
    
    private static final Comic COMIC1 = Comic.builder().title("Asterix").authors(List.of(AUTHOR2, AUTHOR3)).category(Category.CHILDREN).illustrators(List.of(ILLUSTRATOR1)).build();

    private static final User USER1 = User.builder().firstName("Toto").lastName("Blob").birthDate(LocalDate.of(1992,12,12)).userId(1L).build();

    private ArrayList<User> users = new ArrayList<>(Arrays.asList(USER1));


    private List<BookInstance> bookInRentalShop = new ArrayList<>(Arrays.asList(
        new BookInstance(2L, NOVEL1),
        new BookInstance(3L, COMIC1),
        new BookInstance(4L, NOVEL1),
        new BookInstance(5L, COMIC1),
        new BookInstance(6L, NOVEL1)
    ));

    private List<BookRental> bookRentals = new ArrayList<>(Arrays.asList(
        new BookRental(getBookInstanceById(2L), USER1),
        new BookRental(getBookInstanceById(4L), USER1),
        new BookRental(getBookInstanceById(6L), USER1)
    ));

    public List<User> getUsers() {
        return users;
    }

    public void createUser(User user) {
        user.setUserId(incrementAndGetUserSeq());
        users.add(user);
    }

    public User getUserById(Long id) {
        return users.stream()
        .filter(user -> id.equals(user.getUserId()))
        .findFirst().orElseThrow(NotFoundException::new);
    }

    public List<BookInstance> getBookInstances () {
        return bookInRentalShop;
    }

    public BookInstance getBookInstanceById(Long id) {
        return bookInRentalShop.stream()
        .filter(bookInstance -> bookInstance.getBookId().equals(id))
        .findFirst()
        .orElseThrow(NotFoundException::new);
    }

    public List<BookRental> getBookRentals() {
        return bookRentals;
    }

    public void createBookRental(BookRental bookRental) {
        bookRentals.add(bookRental);
    }

    public void createBookInstance(BookInstance bookInstance) {
        bookInstance.setBookId(incrementAndGetBookSeq());
        bookInRentalShop.add(bookInstance);
    }

    private Long incrementAndGetUserSeq() {
        USER_SEQ ++;
        return USER_SEQ;
    }

    private Long incrementAndGetBookSeq() {
        BOOK_SEQ ++;
        return BOOK_SEQ;
    }

}
