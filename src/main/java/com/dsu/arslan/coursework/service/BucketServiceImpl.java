package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.BookRepository;
import com.dsu.arslan.coursework.dao.BucketRepository;
import com.dsu.arslan.coursework.domain.*;
import com.dsu.arslan.coursework.dto.BucketDTO;
import com.dsu.arslan.coursework.dto.BucketDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
    private final OrderService orderService;

    @Autowired
    public BucketServiceImpl(BucketRepository bucketRepository,
                             BookRepository bookRepository,
                             UserService userService,
                             OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> booksIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Book> bookList = getCollectRefBooksByIds(booksIds);
        bucket.setBooks(bookList);
        return bucketRepository.save(bucket);
    }

    private List<Book> getCollectRefBooksByIds(List<Long> productIds) {
        return productIds.stream()
                .map(bookRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> booksIds) {
        List<Book> bookList = bucket.getBooks();
        List<Book> newBookList = bookList == null ? new ArrayList<>() : new ArrayList<>(bookList);
        newBookList.addAll(getCollectRefBooksByIds(booksIds));
        bucket.setBooks(newBookList);
        bucketRepository.save(bucket);
    }

    @Override
    public void removeProducts(Bucket bucket, List<Long> booksIds) {
        List<Book> bookList = bucket.getBooks();
        List<Book> newBookList = bookList == null ? new ArrayList<>() : new ArrayList<>(bookList);
        newBookList.removeAll(getCollectRefBooksByIds(booksIds));
        bucket.setBooks(newBookList);
        bucketRepository.save(bucket);
    }

    @Override
    @Transactional
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
                detailsDTO.setAmount(detailsDTO.getAmount() + 1);
                detailsDTO.setSum(detailsDTO.getSum() + book.getPrice());
            }
        }

        bucketDTO.setBucketDetailsDTOS(new ArrayList<>(mapByBookId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }

    @Override
    @Transactional
    public void commitBucketToOrder(String username) {
        User user = userService.findByUsername(username);

        if(user == null){
            throw new RuntimeException("User is not found");
        }

        Bucket bucket = user.getBucket();

        if(bucket == null || bucket.getBooks().isEmpty()){
            return;
        }

        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);

        Map<Book, Long> bookWithAmount = bucket.getBooks().stream()
                .collect(Collectors.groupingBy(book ->  book, Collectors.counting()));

        List<OrderDetails> orderDetails = bookWithAmount.entrySet().stream()
                .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());

        Double total = orderDetails.stream()
                .map(detail -> detail.getPrice() * detail.getAmount())
                .mapToDouble(Double::doubleValue)
                .sum();

//        BigDecimal total = new BigDecimal(orderDetails.stream()
//                .map(detail -> detail.getPrice().multiply(detail.getAmount()))
//                .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetailsList(orderDetails);
        order.setSum(total);
        order.setAddress("none");

        orderService.saveOrder(order);
        bucket.getBooks().clear();
        bucketRepository.save(bucket);
    }

}
