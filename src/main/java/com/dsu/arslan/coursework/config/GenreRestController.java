package com.dsu.arslan.coursework.config;

import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreRestController {

    private final GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{id}")
    public GenreDTO getById(@PathVariable Long id){
        return genreService.getById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody GenreDTO dto){
        genreService.addGenre(dto);
    }

}
