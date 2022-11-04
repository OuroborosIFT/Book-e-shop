package com.dsu.arslan.coursework.mapper;

import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.dto.GenreDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper GENRE_MAPPER = Mappers.getMapper(GenreMapper.class);

    Genre toGenre(GenreDTO genreDTO);

    @InheritInverseConfiguration
    GenreDTO fromGenre(Genre genre);

    List<Genre> toGenreList(List<GenreDTO> genreDTOS);

    List<GenreDTO> fromGenreList(List<Genre> genres);

}
