package com.dsu.arslan.coursework.mapper;

import com.dsu.arslan.coursework.domain.Author;
import com.dsu.arslan.coursework.dto.AuthorDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {

    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);

    Author toAuthor(AuthorDTO authorDTO);

    @InheritInverseConfiguration
    AuthorDTO fromAuthor(Author author);

    List<Author> toAuthorList(List<AuthorDTO> authorDTOS);

    List<AuthorDTO> fromAuthorList(List<Author> authors);

}