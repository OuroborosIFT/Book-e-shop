package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    // сохраняет пользователя в базу
    boolean save(UserDTO userDTO);
    void save(User user);
    List<UserDTO> getAll();
    User findByUsername(String username);
    void updateProfile(UserDTO userDTO);

}
