package com.dsu.arslan.coursework.endpoint;

import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.service.GenreService;
import com.dsu.arslan.coursework.ws.genres.GenresWS;
import com.dsu.arslan.coursework.ws.genres.GetGenresRequest;
import com.dsu.arslan.coursework.ws.genres.GetGenresResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GenresEndpoint {

    public static final String NAMESPACE_URL = "https://arslan.dsu.com/coursework/ws/genres";

    private final GenreService genreService;

    @Autowired
    public GenresEndpoint(GenreService genreService) {
        this.genreService = genreService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getGenresRequest")
    @ResponsePayload
    public GetGenresResponse getGreeting(@RequestPayload GetGenresRequest request) throws DatatypeConfigurationException {
        GetGenresResponse response = new GetGenresResponse();
        genreService.getAllGenres()
                .forEach(dto -> response.getGenres().add(createGenreWS(dto)));
        return response;
    }

    private GenresWS createGenreWS(GenreDTO dto) {
        GenresWS ws = new GenresWS();
        ws.setId(dto.getId());
        ws.setTitle(dto.getTitle());
        return ws;
    }

}
