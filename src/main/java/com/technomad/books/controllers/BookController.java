package com.technomad.books.controllers;


import com.technomad.books.domain.dto.BookDto;
import com.technomad.books.domain.entities.BookEntity;
import com.technomad.books.mappers.Mapper;
import com.technomad.books.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/{isbn}")
    public BookDto getBookByIsbn(@PathVariable("isbn") String isbn) {
        return this.bookService.findBookById(isbn)
                .map(this.bookMapper::mapTo)
                .orElse(null);
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookEntity bookEntity = this.bookMapper.mapFrom(bookDto);
        BookEntity createdBook = this.bookService.createBook(bookEntity);
        return new ResponseEntity<>(this.bookMapper.mapTo(createdBook), HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = this.bookMapper.mapFrom(bookDto);
        bookEntity.setIsbn(isbn);
        BookEntity updatedBook = this.bookService.updateBook(bookEntity);
        return new ResponseEntity<>(this.bookMapper.mapTo(updatedBook), HttpStatus.OK);
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = this.bookMapper.mapFrom(bookDto);
        BookEntity updatedBook = this.bookService.partialUpdateBook(isbn, bookEntity);
        return new ResponseEntity<>(this.bookMapper.mapTo(updatedBook), HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") String isbn) {
        this.bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public List<BookDto> getAllBooks() {
        return this.bookService.findAllBooks().stream()
                .map(this.bookMapper::mapTo)
                .toList();
    }

}
