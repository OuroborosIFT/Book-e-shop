package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final SessionObjectHolder sessionObjectHolder;

    public BookController(BookService bookService, SessionObjectHolder sessionObjectHolder) {
        this.bookService = bookService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
    public String booksList(Model model) {
        sessionObjectHolder.addClick();
        List<BookDTO> bookDTOS = bookService.getAllBooks();
        model.addAttribute("books", bookDTOS);
        return "books";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal) {
        sessionObjectHolder.addClick();

        if (principal == null) {
            return "redirect:/books";
        }

        bookService.addToUserBucket(id, principal.getName());
        return "redirect:/books";
    }

}
