package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    GenreDTO getGenreById(Long id);
    List<GenreDTO> getAllGenres();
    void addGenre(GenreDTO genreDTO);
    GenreDTO getById(Long id);
}
