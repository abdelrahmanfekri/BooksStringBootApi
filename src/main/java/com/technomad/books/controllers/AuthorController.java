package com.technomad.books.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.technomad.books.services.AuthorService;
import com.technomad.books.mappers.Mapper;
import com.technomad.books.domain.dto.AuthorDto;
import com.technomad.books.domain.entities.AuthorEntity;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Long id) {
        return this.authorService.findAuthorById(id)
                .map(authorEntity -> new ResponseEntity<>(this.authorMapper.mapTo(authorEntity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public List<AuthorDto> getAllAuthors() {
        return this.authorService.findAllAuthors()
                .stream()
                .map(this.authorMapper::mapTo)
                .toList();
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = this.authorMapper.mapFrom(authorDto);
        AuthorEntity createdAuthor = this.authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(this.authorMapper.mapTo(createdAuthor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = this.authorMapper.mapFrom(authorDto);
        if(!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorEntity updatedAuthor = this.authorService.updateAuthor(authorEntity);
        return new ResponseEntity<>(this.authorMapper.mapTo(updatedAuthor), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public AuthorDto partialUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = this.authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor = this.authorService.partialUpdateAuthor(id, authorEntity);
        return this.authorMapper.mapTo(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        this.authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/exists/{id}")
    public boolean isExists(@PathVariable("id") Long id) {
        return this.authorService.isExists(id);
    }

}
