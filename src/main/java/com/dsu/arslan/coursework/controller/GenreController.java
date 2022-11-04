package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String genresList(Model model) {
        List<GenreDTO> genreDTOS = genreService.getAllGenres();
        model.addAttribute("genres", genreDTOS);
        return "genres";
    }

}
