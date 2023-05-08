package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Book;
import com.dsu.arslan.coursework.dto.BookDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all test");
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each test");
    }

    @Test
    void checkFindByGenre() {
        List<Book> list = repository.findAllByGenre(1L);

        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    void checkFindByKeyword() {
        List<Book> list = repository.findByKeyword("Ревизор");

        for (Book book : list) {
            System.out.println(book);
        }
    }

}
