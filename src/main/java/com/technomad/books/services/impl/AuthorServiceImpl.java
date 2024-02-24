package com.technomad.books.services.impl;

import com.technomad.books.domain.entities.AuthorEntity;
import com.technomad.books.repositories.AuthorRepository;
import com.technomad.books.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorEntity updateAuthor(AuthorEntity author) {
        return this.authorRepository.save(author);
    }

    @Override
    public List<AuthorEntity> findAllAuthors() {
        return (List<AuthorEntity>) this.authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> findAuthorById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return this.authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return this.authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdateAuthor(Long id, AuthorEntity author) {
        this.authorRepository.findById(id).ifPresent(a -> {
            if (author.getName() != null) {
                a.setName(author.getName());
            }
            if (author.getAge() != 0) {
                a.setAge(author.getAge());
            }
            this.authorRepository.save(a);
        });
        return this.authorRepository.findById(id).get();
    }
}
