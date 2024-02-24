package com.technomad.books.repository;


import com.technomad.books.TestDataUtil;
import com.technomad.books.domain.entities.AuthorEntity;
import com.technomad.books.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepoTest {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRepoTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    public void testCreateAuthorAndFindById() {
        AuthorEntity authorA = TestDataUtil.createTestAuthorEntityA();
        this.authorRepository.save(authorA);
        Optional<AuthorEntity> authorEntity = this.authorRepository.findById(authorA.getId());
        assert authorEntity.isPresent();
        assert authorEntity.get().getId().equals(authorA.getId());
        assert authorEntity.get().getName().equals(authorA.getName());
    }

    @Test
    public void testCreateMultipleAuthorsAndRecall(){
        AuthorEntity authorA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        this.authorRepository.save(authorA);
        this.authorRepository.save(authorB);
        assert this.authorRepository.count() == 2;
    }

    @Test
    public void testCreateAuthorAndDelete(){
        AuthorEntity authorA = TestDataUtil.createTestAuthorEntityA();
        this.authorRepository.save(authorA);
        assert this.authorRepository.count() == 1;
        this.authorRepository.delete(authorA);
        assert this.authorRepository.count() == 0;
    }

    @Test
    public void testCreateAuthorAndUpdate() {
        AuthorEntity authorA = TestDataUtil.createTestAuthorEntityA();
        this.authorRepository.save(authorA);
        assert this.authorRepository.count() == 1;
        authorA.setName("Abigail Rose Cronin");
        this.authorRepository.save(authorA);
        Optional<AuthorEntity> authorEntity = this.authorRepository.findById(authorA.getId());
        assert authorEntity.isPresent();
        assert authorEntity.get().getName().equals("Abigail Rose Cronin");
    }

}
