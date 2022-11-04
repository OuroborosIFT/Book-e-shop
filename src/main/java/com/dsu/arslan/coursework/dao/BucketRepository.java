package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
