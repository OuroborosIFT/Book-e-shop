package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.AuthorDTO;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.AuthorService;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final SessionObjectHolder sessionObjectHolder;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public MainController(SessionObjectHolder sessionObjectHolder, BookService bookService, AuthorService authorService) {
        this.sessionObjectHolder = sessionObjectHolder;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping(path = {"", "/", "/index", "/index.html", "/home"})
    public String index(Model model, HttpSession httpSession) {
        sessionObjectHolder.addClick();
        List<BookDTO> bookDTOS = bookService.getAllBooks();
        model.addAttribute("books", bookDTOS);

        model.addAttribute("amountClicks", sessionObjectHolder.getAmountClicks());

        if (httpSession.getAttribute("myID") == null) {
            String uuid = UUID.randomUUID().toString();
            httpSession.setAttribute("myID", uuid);
            System.out.println("Generated UUID -> " + uuid);
        }

        model.addAttribute("uuid", httpSession.getAttribute("myID"));
        return "index";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(path = {"/about", "/about.html"})
    public String about() {
        return "about";
    }

    @RequestMapping(path = {"/contact", "/contact.html"})
    public String contact() {
        return "contact";
    }




    @RequestMapping(path = "/di")
    public String demoIndex() {
        return "demo_index";
    }

    @GetMapping(path = "/search")
    public String search(String keyword, Model model) {
//        List<BookDTO> bookDTOS = searchBook(keyword);
        List<BookDTO> bookDTOS = bookService.getByKeyword(keyword);
//        List<AuthorDTO> authorDTOS = searchAuthor(keyword);
        List<AuthorDTO> authorDTOS = authorService.getByKeyword(keyword);
        model.addAttribute("books", bookDTOS);
        model.addAttribute("authors", authorDTOS);

        return "search";
    }

    /*private List<AuthorDTO> searchAuthor(String keyword) {
        List<AuthorDTO> list = authorService.getAllAuthors();
        List<AuthorDTO> newList = new ArrayList<>();

        for (AuthorDTO dto : list) {
            if (dto.getName().equalsIgnoreCase(keyword)
                    || dto.getLastname().equalsIgnoreCase(keyword)
                    || dto.getPatronymic().equalsIgnoreCase(keyword)) {
                newList.add(dto);
            }
        }

        return newList;
    }
    private List<BookDTO> searchBook(String keyword) {
        List<BookDTO> list = bookService.getAllBooks();
        List<BookDTO> newList = new ArrayList<>();

        for (BookDTO dto : list) {
            if (dto.getTitle().equalsIgnoreCase(keyword)) {
                newList.add(dto);
            }
        }

        return newList;
    }*/

}
