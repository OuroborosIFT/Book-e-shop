package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.BookRepository;
import com.dsu.arslan.coursework.dao.UserRepository;
import com.dsu.arslan.coursework.dto.BookDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class BookServiceImplTest {

    @Autowired
    private BookRepository repository;
    @Autowired
    private BookService service;

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
    void checkByGenre() {
        List<BookDTO> list = service.getBooksByGenre(1L);

        for (BookDTO dto : list) {
            System.out.println(dto);
        }
    }

}
