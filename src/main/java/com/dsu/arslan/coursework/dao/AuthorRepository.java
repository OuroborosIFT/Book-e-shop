package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM authors WHERE name LIKE %:keyword% " +
                                            "OR lastname LIKE %:keyword% " +
                                            "OR patronymic LIKE %:keyword%",
            nativeQuery = true)
    List<Author> findByKeyword(@Param("keyword") String keyword);

}
