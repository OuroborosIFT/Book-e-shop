package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.dto.GenreDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    List<BookDTO> getBooksByGenre(Long id);
    void addToUserBucket(Long bookId, String username);
    void removeFromUserBucket(Long bookId, String username);
    void addBook(BookDTO bookDTO);
    BookDTO getById(Long id);
}
