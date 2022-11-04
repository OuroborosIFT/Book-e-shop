package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
