package com.dsu.arslan.coursework.service.impl;

import com.dsu.arslan.coursework.dao.GenreRepository;
import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.mapper.GenreMapper;
import com.dsu.arslan.coursework.service.GenreService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper = GenreMapper.GENRE_MAPPER;

    private final GenreRepository genreRepository;
    private final SimpMessagingTemplate template;

    public GenreServiceImpl(GenreRepository genreRepository, SimpMessagingTemplate template) {
        this.genreRepository = genreRepository;
        this.template = template;
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        return genreMapper.fromGenre(genreRepository.getReferenceById(id));
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreMapper.fromGenreList(genreRepository.findAll());
    }

    @Override
    @Transactional
    public void addGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.toGenre(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        template.convertAndSend("/topic/genres", GenreMapper.GENRE_MAPPER.fromGenre(savedGenre));
    }

    @Override
    public GenreDTO getById(Long id) {
        Genre genre = genreRepository.findById(id).orElse(new Genre());
        return GenreMapper.GENRE_MAPPER.fromGenre(genre);
    }
}
