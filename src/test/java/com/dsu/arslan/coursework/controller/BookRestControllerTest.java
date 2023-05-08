package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.config.BookRestController;
import com.dsu.arslan.coursework.domain.Author;
import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {
    private List<Author> testAuthorsList;
    private List<Genre> testGenresList;

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private BookService bookService;
        @MockBean
        private UserService userService;

        private BookDTO dto =  new BookDTO(999L, "Test Book", "Book description", "Book image", 99.9, testAuthorsList, testGenresList);

        @BeforeEach
        void setUp() {
        given(bookService.getById(dto.getId())).willReturn(dto);
        }

        @Test
        void getById() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/books/{id}", dto.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(999)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Book")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(99.9)));

        }

}
