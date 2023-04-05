package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE genre_id = :id", nativeQuery = true)
    List<Book> findAllByGenre(@Param("id") Long id);

}
