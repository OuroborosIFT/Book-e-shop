package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Author;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

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
    void checkFindByKeyword() {
        List<Author> list = repository.findByKeyword("Лев");

        for (Author author : list) {
            System.out.println(author);
        }
    }

}
