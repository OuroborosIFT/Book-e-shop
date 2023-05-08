package com.dsu.arslan.coursework.endpoint;

import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.ws.books.BooksWS;
import com.dsu.arslan.coursework.ws.books.GetBooksRequest;
import com.dsu.arslan.coursework.ws.books.GetBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class BooksEndpoint {

    public static final String NAMESPACE_URL = "https://arslan.dsu.com/coursework/ws/books";

    private final BookService bookService;

    @Autowired
    public BooksEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getGreeting(@RequestPayload GetBooksRequest request) throws DatatypeConfigurationException {
        GetBooksResponse response = new GetBooksResponse();
        bookService.getAllBooks()
                .forEach(dto -> response.getBooks().add(createBookWS(dto)));
        return response;
    }

    private BooksWS createBookWS(BookDTO dto) {
        BooksWS ws = new BooksWS();
        ws.setId(dto.getId());
        ws.setTitle(dto.getTitle());
        ws.setImage(dto.getImage());
        ws.setDescription(dto.getDescription());
        ws.setPrice(dto.getPrice());
        return ws;
    }

}
