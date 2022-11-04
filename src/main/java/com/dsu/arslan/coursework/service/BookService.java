package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    void addToUserBucket(Long bookId, String username);
}
