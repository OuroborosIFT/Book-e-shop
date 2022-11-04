package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.domain.Bucket;
import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.BucketDTO;

import java.util.List;

public interface BucketService {

    Bucket createBucket(User user, List<Long> productsIds);

    void addProducts(Bucket bucket, List<Long> productsIds);

    BucketDTO getBucketByUser(String username);

}
