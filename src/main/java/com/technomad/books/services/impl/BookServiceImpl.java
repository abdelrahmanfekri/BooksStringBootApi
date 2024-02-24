package com.technomad.books.services.impl;

import com.technomad.books.domain.entities.BookEntity;
import com.technomad.books.repositories.BookRepository;
import com.technomad.books.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
   private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
         this.bookRepository = bookRepository;
    }


    @Override
    public BookEntity updateBook(BookEntity book) {
        return this.bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAllBooks() {
        return (List<BookEntity>) this.bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findBookById(String isbn) {
        return this.bookRepository.findById(isbn);
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(String isbn) {
        this.bookRepository.deleteById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return this.bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdateBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return this.bookRepository.findById(isbn).map(b -> {
            if (book.getTitle() != null) {
                b.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                b.setAuthor(book.getAuthor());
            }
            return this.bookRepository.save(b);
        }).orElseGet(() -> this.bookRepository.save(book));
    }
}
