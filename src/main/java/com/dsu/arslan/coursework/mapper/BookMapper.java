package com.dsu.arslan.coursework.mapper;

import com.dsu.arslan.coursework.domain.Book;
import com.dsu.arslan.coursework.dto.BookDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    Book toBook(BookDTO bookDTO);

    @InheritInverseConfiguration
    BookDTO fromBook(Book book);

    List<Book> toBookList(List<BookDTO> bookDTOS);

    List<BookDTO> fromBookList(List<Book> books);

}
