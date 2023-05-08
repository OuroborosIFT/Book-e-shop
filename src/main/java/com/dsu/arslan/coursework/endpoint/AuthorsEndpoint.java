package com.dsu.arslan.coursework.endpoint;

import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.service.AuthorService;
import com.dsu.arslan.coursework.ws.authors.AuthorsWS;
import com.dsu.arslan.coursework.ws.authors.GetAuthorsRequest;
import com.dsu.arslan.coursework.ws.authors.GetAuthorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class AuthorsEndpoint {

    public static final String NAMESPACE_URL = "https://arslan.dsu.com/coursework/ws/authors";

    private final AuthorService authorService;

    @Autowired
    public AuthorsEndpoint(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getAuthorsRequest")
    @ResponsePayload
    public GetAuthorsResponse getGreeting(@RequestPayload GetAuthorsRequest request) throws DatatypeConfigurationException {
        GetAuthorsResponse response = new GetAuthorsResponse();
        authorService.getAllAuthors()
                .forEach(dto -> response.getAuthors().add(createAuthorsWS(dto)));
        return response;
    }

    private AuthorsWS createAuthorsWS(AuthorDTO dto) {
        AuthorsWS ws = new AuthorsWS();
        ws.setId(dto.getId());
        ws.setName(dto.getName());
        ws.setLastname(dto.getLastname());
        ws.setPatronymic(dto.getPatronymic());
        ws.setPortrait(dto.getPortrait());
        ws.setAbout(dto.getAbout());
        return ws;
    }

}
