package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.domain.Author;
import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    private List<Author> testAuthorsList;
    private List<Genre> testGenresList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private UserService userService;
    @MockBean
    private SessionObjectHolder sessionObjectHolder;

    private BookDTO dto1 = new BookDTO(97L, "Test Book", "Book description", "Book image", 19.9, testAuthorsList, testGenresList);
    private BookDTO dto2 = new BookDTO(98L, "Test Book", "Book description", "Book image", 29.9, testAuthorsList, testGenresList);

    @BeforeEach
    void setUp() {
        given(bookService.getAllBooks()).willReturn(Arrays.asList(dto1, dto2));
    }

    @Test
    void checkList() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + dto1.getTitle() + "</td>")))
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + dto2.getTitle() + "</td>")));

    }

}
