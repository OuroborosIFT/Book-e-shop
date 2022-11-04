package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.BookRepository;
import com.dsu.arslan.coursework.domain.Bucket;
import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper = BookMapper.BOOK_MAPPER;

    private final BookRepository bookRepository;
    private final UserService userService;
    private final BucketService bucketService;

    public BookServiceImpl(BookRepository bookRepository, UserService userService, BucketService bucketService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookMapper.fromBookList(bookRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long bookId, String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User \"" + username + "\" not found");
        }

        Bucket bucket = user.getBucket();

        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(bookId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(bookId));
        }
    }
}
