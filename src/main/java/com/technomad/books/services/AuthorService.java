package com.technomad.books.services;

import com.technomad.books.domain.entities.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity updateAuthor(AuthorEntity author);
    List<AuthorEntity> findAllAuthors();
    Optional<AuthorEntity> findAuthorById(Long id);
    AuthorEntity createAuthor(AuthorEntity author);
    void deleteAuthor(Long id);
    boolean isExists(Long id);

    AuthorEntity partialUpdateAuthor(Long id, AuthorEntity author);
}
