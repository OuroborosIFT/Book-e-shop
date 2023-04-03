package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.dto.UserDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final SessionObjectHolder sessionObjectHolder;
    private final BookService bookService;

    public MainController(SessionObjectHolder sessionObjectHolder, BookService bookService) {
        this.sessionObjectHolder = sessionObjectHolder;
        this.bookService = bookService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
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

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @RequestMapping({"/signup", "/signup.html"})
//    public String signup(Model model) {
//        model.addAttribute("user", new UserDTO());
//        return "signup";
//    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping({"/about", "/about.html"})
    public String about() {
        return "about";
    }

    @RequestMapping({"/contact", "/contact.html"})
    public String contact() {
        return "contact";
    }

 /*   @RequestMapping({"/products", "/products.html"})
    public String products() {
        return "products";
    }*/

}
