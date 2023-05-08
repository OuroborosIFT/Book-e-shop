package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/genres", "/genres.html"})
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String genresList(Model model) {
        List<GenreDTO> genreDTOS = genreService.getAllGenres();
        model.addAttribute("genres", genreDTOS);
        return "genres";
    }

    @PostMapping
    public ResponseEntity<Void> addGenre(GenreDTO dto) {
        genreService.addGenre(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/genres")
    public void messageAddProduct(GenreDTO dto) {
        genreService.addGenre(dto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public GenreDTO getById(@PathVariable Long id) {
        return genreService.getById(id);
    }

}
