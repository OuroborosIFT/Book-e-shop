package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.AuthorRepository;
import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper = AuthorMapper.AUTHOR_MAPPER;

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorMapper.fromAuthorList(authorRepository.findAll());
    }
}
