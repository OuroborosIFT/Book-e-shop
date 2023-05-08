package com.dsu.arslan.coursework.config;

import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody BookDTO dto){
        bookService.addBook(dto);
    }

}
