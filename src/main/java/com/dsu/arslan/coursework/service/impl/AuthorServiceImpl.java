package com.dsu.arslan.coursework.service.impl;

import com.dsu.arslan.coursework.dao.AuthorRepository;
import com.dsu.arslan.coursework.domain.Author;
import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.mapper.AuthorMapper;
import com.dsu.arslan.coursework.service.AuthorService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper = AuthorMapper.AUTHOR_MAPPER;

    private final AuthorRepository authorRepository;
    private final SimpMessagingTemplate template;

    public AuthorServiceImpl(AuthorRepository authorRepository, SimpMessagingTemplate template) {
        this.authorRepository = authorRepository;
        this.template = template;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorMapper.fromAuthorList(authorRepository.findAll());
    }

    @Override
    public List<AuthorDTO> getByKeyword(String keyword) {
        return authorMapper.fromAuthorList(authorRepository.findByKeyword(keyword));
    }

    @Override
    public AuthorDTO getById(Long id) {
        Author author = authorRepository.findById(id).orElse(new Author());
        return authorMapper.fromAuthor(author);
    }

    @Override
    @Transactional
    public void addAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        template.convertAndSend("/topic/authors", AuthorMapper.AUTHOR_MAPPER.fromAuthor(savedAuthor));
    }

}
