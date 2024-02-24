package com.technomad.books.services;
import com.technomad.books.domain.entities.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity updateBook(BookEntity book);
    List<BookEntity> findAllBooks();
    Optional<BookEntity> findBookById(String isbn);
    BookEntity createBook(BookEntity book);
    void deleteBook(String isbn);
    boolean isExists(String isbn);
    BookEntity partialUpdateBook(String isbn, BookEntity book);
}
