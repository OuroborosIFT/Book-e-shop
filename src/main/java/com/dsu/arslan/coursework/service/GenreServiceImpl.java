package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.GenreRepository;
import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.mapper.GenreMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper = GenreMapper.GENRE_MAPPER;

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreMapper.fromGenreList(genreRepository.findAll());
    }
}
