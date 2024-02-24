package com.technomad.books.repository;


import com.technomad.books.TestDataUtil;
import com.technomad.books.domain.entities.BookEntity;
import com.technomad.books.repositories.BookRepository;
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
public class BookRepoTest {

    private final BookRepository bookRepository;

    @Autowired
    public BookRepoTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testCreateBookAndFindById() {
        BookEntity bookA = TestDataUtil.createTestBookEntityA(TestDataUtil.createTestAuthorEntityA());
        this.bookRepository.save(bookA);
        Optional<BookEntity> bookEntity = this.bookRepository.findById(bookA.getIsbn());
        assert bookEntity.isPresent();
        assert bookEntity.get().getIsbn().equals(bookA.getIsbn());
        assert bookEntity.get().getTitle().equals(bookA.getTitle());
        assert bookEntity.get().getAuthor().getId().equals(bookA.getAuthor().getId());
    }

    @Test
    public void testCreateMultipleBooksAndRecall(){
        BookEntity bookA = TestDataUtil.createTestBookEntityA(TestDataUtil.createTestAuthorEntityA());
        BookEntity bookB = TestDataUtil.createTestBookB(TestDataUtil.createTestAuthorB());
        this.bookRepository.save(bookA);
        this.bookRepository.save(bookB);
        assert this.bookRepository.count() == 2;
    }

    @Test
    public void testCreateBookAndDelete(){
        BookEntity bookA = TestDataUtil.createTestBookEntityA(TestDataUtil.createTestAuthorEntityA());
        this.bookRepository.save(bookA);
        assert this.bookRepository.count() == 1;
        this.bookRepository.delete(bookA);
        assert this.bookRepository.count() == 0;
    }

    @Test
    public void testCreateBookAndUpdate() {
        BookEntity bookA = TestDataUtil.createTestBookEntityA(TestDataUtil.createTestAuthorEntityA());
        this.bookRepository.save(bookA);
        assert this.bookRepository.count() == 1;
        bookA.setTitle("New Title");
        this.bookRepository.save(bookA);
        Optional<BookEntity> bookEntity = this.bookRepository.findById(bookA.getIsbn());
        assert bookEntity.isPresent();
        assert bookEntity.get().getTitle().equals("New Title");
    }

}
