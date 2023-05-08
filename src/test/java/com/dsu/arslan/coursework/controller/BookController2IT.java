package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.domain.Author;
import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookController2IT {

    private List<Author> testAuthorsList;
    private List<Genre> testGenresList;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BookService bookService;

    private BookDTO expectedBook = new BookDTO(99L, "Test Book", "Book description", "Book image", 99.9, testAuthorsList, testGenresList);

    @BeforeEach
    void setUp() {
        given(bookService.getById(expectedBook.getId()))
                .willReturn(expectedBook);
    }

    @Test
    void getById() {
        //execute
        ResponseEntity<BookDTO> entity =
                restTemplate
                        .getForEntity("/books/" + expectedBook.getId(), BookDTO.class);
        //check
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());

        BookDTO actualBook = entity.getBody();
        Assertions.assertEquals(expectedBook, actualBook);

    }

    @Test
    void addBook() {
        //execute
        ResponseEntity<Void> response =
                restTemplate.postForEntity("/api/v1/books/", expectedBook, Void.class);
        //check
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(bookService).addBook(Mockito.eq(expectedBook));

        ArgumentCaptor<BookDTO> captor = ArgumentCaptor.forClass(BookDTO.class);
        verify(bookService).addBook(captor.capture());

        Assertions.assertEquals(expectedBook, captor.getValue());
    }

}
