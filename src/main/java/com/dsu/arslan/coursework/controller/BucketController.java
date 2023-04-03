package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.dto.BucketDTO;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.BucketService;
import com.dsu.arslan.coursework.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService bucketService;
    private final BookService bookService;
    private final SessionObjectHolder sessionObjectHolder;

    public BucketController(BucketService bucketService, BookService bookService, SessionObjectHolder sessionObjectHolder) {
        this.bucketService = bucketService;
        this.bookService = bookService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        } else {
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }

        return "bucket";
    }

    @PostMapping
    public String commitBucket(Principal principal){
        if(principal != null){
            bucketService.commitBucketToOrder(principal.getName());
        }

        return "redirect:/bucket";
    }

    @GetMapping("/{id}/book")
    public String removeFromBucket(@PathVariable Long id, Principal principal) {
        sessionObjectHolder.addClick();

        if (principal == null) {
            return "redirect:/bucket";
        }

        bookService.removeFromUserBucket(id, principal.getName());
        return "redirect:/bucket";
    }

}
