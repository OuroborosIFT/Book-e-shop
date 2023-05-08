package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/products", "/products.html"})
public class ProductController {

    private final BookService bookService;
    private final SessionObjectHolder sessionObjectHolder;

    @Autowired
    public ProductController(BookService bookService, SessionObjectHolder sessionObjectHolder) {
        this.bookService = bookService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
    public String productsList(Model model) {
        sessionObjectHolder.addClick();
        List<BookDTO> bookDTOS = bookService.getAllBooks();
        model.addAttribute("books", bookDTOS);
        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addToBucket(@PathVariable Long id, Principal principal) {
        sessionObjectHolder.addClick();

        if (principal == null) {
            return "redirect:/products";
        }

        bookService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }

    @PostMapping
    public ResponseEntity<Void> addBook(BookDTO dto) {
        bookService.addBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/products")
    public void messageAddProduct(BookDTO dto) {
        bookService.addBook(dto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/genre/{id}")
    public String booksByGenre(@PathVariable Long id, Model model) {
        List<BookDTO> bookDTOS = bookService.getBooksByGenre(id);
        model.addAttribute("books", bookDTOS);
        return "products";
    }

}
