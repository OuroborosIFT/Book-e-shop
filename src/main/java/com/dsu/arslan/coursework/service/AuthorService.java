package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    List<AuthorDTO> getByKeyword(String keyword);
    AuthorDTO getById(Long id);
    void addAuthor(AuthorDTO authorDTO);

}
