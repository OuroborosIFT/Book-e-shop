package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
}
