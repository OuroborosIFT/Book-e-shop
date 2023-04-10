package com.dsu.arslan.coursework.service.impl;

import com.dsu.arslan.coursework.dao.BookRepository;
import com.dsu.arslan.coursework.domain.Book;
import com.dsu.arslan.coursework.domain.Bucket;
import com.dsu.arslan.coursework.domain.Genre;
import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.BookDTO;
import com.dsu.arslan.coursework.dto.GenreDTO;
import com.dsu.arslan.coursework.mapper.BookMapper;
import com.dsu.arslan.coursework.service.BookService;
import com.dsu.arslan.coursework.service.BucketService;
import com.dsu.arslan.coursework.service.UserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate template;

    public BookServiceImpl(BookRepository bookRepository,
                           UserService userService,
                           BucketService bucketService,
                           SimpMessagingTemplate template) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.template = template;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookMapper.fromBookList(bookRepository.findAll());
    }

    @Override
    public List<BookDTO> getBooksByGenre(Long id) {
        return bookMapper.fromBookList(bookRepository.findAllByGenre(id));
    }

    @Override
    public List<BookDTO> getByKeyword(String keyword) {
        return bookMapper.fromBookList(bookRepository.findByKeyword(keyword));
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

    @Override
    @Transactional
    public void removeFromUserBucket(Long bookId, String username) {
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
            bucketService.removeProducts(bucket, Collections.singletonList(bookId));
        }
    }

    @Override
    @Transactional
    public void addBook(BookDTO bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        template.convertAndSend("/topic/books", BookMapper.BOOK_MAPPER.fromBook(savedBook));
    }

    @Override
    public BookDTO getById(Long id) {
        Book product = bookRepository.findById(id).orElse(new Book());
        return BookMapper.BOOK_MAPPER.fromBook(product);
    }

}
