package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.service.AuthorService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/authors", "/authors.html"})
public class AuthorController {

    private final AuthorService authorService;
    private final SessionObjectHolder sessionObjectHolder;

    public AuthorController(AuthorService authorService, SessionObjectHolder sessionObjectHolder) {
        this.authorService = authorService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
    public String authorsList(Model model) {
        sessionObjectHolder.addClick();
        List<AuthorDTO> authorDTOS = authorService.getAllAuthors();
        model.addAttribute("authors", authorDTOS);
        return "authors";
    }

    @PostMapping
    public ResponseEntity<Void> addAuthor(AuthorDTO dto) {
        authorService.addAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/authors")
    public void messageAddProduct(AuthorDTO dto) {
        authorService.addAuthor(dto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AuthorDTO getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

}
