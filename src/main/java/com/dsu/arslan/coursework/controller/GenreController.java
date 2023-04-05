package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/genres", "/genres.html"})
public class GenreController {

    private final GenreService genreService;
    private final BookService bookService;

    public GenreController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @GetMapping
    public String genresList(Model model) {
        List<GenreDTO> genreDTOS = genreService.getAllGenres();
        model.addAttribute("genres", genreDTOS);
        return "genres";
    }

    /*@GetMapping("/{id}")
    public String booksByGenre(@PathVariable Long id, Model model) {
        List<BookDTO> bookDTOS = bookService.getBooksByGenre(id);
        model.addAttribute("books", bookDTOS);
        return "books";
    }*/

}
