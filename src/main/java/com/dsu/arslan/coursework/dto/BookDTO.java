package com.dsu.arslan.coursework.dto;

import com.dsu.arslan.coursework.domain.Author;
import com.dsu.arslan.coursework.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private Double price;
    private List<Author> authors;
    private List<Genre> genres;

}
