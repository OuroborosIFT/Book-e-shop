package com.dsu.arslan.coursework.config;

import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable Long id){
        return authorService.getById(id);
    }

}
