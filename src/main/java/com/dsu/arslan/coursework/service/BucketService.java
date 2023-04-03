package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.domain.Bucket;
import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.BucketDTO;

import java.util.List;

public interface BucketService {

    Bucket createBucket(User user, List<Long> booksIds);

    void addProducts(Bucket bucket, List<Long> booksIds);
    void removeProducts(Bucket bucket, List<Long> booksIds);

    BucketDTO getBucketByUser(String username);

    void commitBucketToOrder(String username);

}
