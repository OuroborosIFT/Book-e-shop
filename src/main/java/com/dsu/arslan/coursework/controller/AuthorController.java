package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String authorsList(Model model) {
        List<AuthorDTO> authorDTOS = authorService.getAllAuthors();
        model.addAttribute("authors", authorDTOS);
        return "authors";
    }

}
