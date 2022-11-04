package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.BookRepository;
import com.dsu.arslan.coursework.dao.BucketRepository;
import com.dsu.arslan.coursework.domain.Book;
import com.dsu.arslan.coursework.domain.Bucket;
import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.BucketDTO;
import com.dsu.arslan.coursework.dto.BucketDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final BookRepository bookRepository;
    private final UserService userService;

    @Autowired
    public BucketServiceImpl(BucketRepository bucketRepository, BookRepository bookRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productsIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Book> bookList = getCollectRefBooksByIds(productsIds);
        bucket.setBooks(bookList);
        return bucketRepository.save(bucket);
    }

    private List<Book> getCollectRefBooksByIds(List<Long> productIds) {
        return productIds.stream()
                .map(bookRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productsIds) {
        List<Book> bookList = bucket.getBooks();
        List<Book> newBookList = bookList == null ? new ArrayList<>() : new ArrayList<>(bookList);
        newBookList.addAll(getCollectRefBooksByIds(productsIds));
        bucket.setBooks(newBookList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String username) {
        User user = userService.findByUsername(username);

        if (user == null || user.getBucket() == null) {
            return new BucketDTO();
        }

        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailsDTO> mapByBookId = new HashMap<>();
        List<Book> bookList = user.getBucket().getBooks();

        for (Book book : bookList) {
            BucketDetailsDTO detailsDTO = mapByBookId.get(book.getId());

            if (detailsDTO == null) {
                mapByBookId.put(book.getId(), new BucketDetailsDTO(book));
            } else {
                detailsDTO.setAmount(detailsDTO.getAmount() + Integer.valueOf("1"));
                detailsDTO.setSum(detailsDTO.getSum() + Double.valueOf(book.getPrice().toString()));
            }
        }

        bucketDTO.setBucketDetailsDTOS(new ArrayList<>(mapByBookId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }
}
