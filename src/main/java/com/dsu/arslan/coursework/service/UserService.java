package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDTO userDTO);  // сохраняет пользователя в базу
    void save(User user);
    List<UserDTO> getAll();
    User findByUsername(String username);
    void updateProfile(UserDTO userDTO);

}
