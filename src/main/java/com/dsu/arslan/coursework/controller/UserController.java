package com.dsu.arslan.coursework.controller;

import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.UserDTO;
import com.dsu.arslan.coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String newUser(Model model) {   // аналог signup()
        System.out.println("Called method \'newUser\'");
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        System.out.println("Called method \'saveUser\'");

        if (userService.save(userDTO)) {
            return "redirect:/users";
        } else {
            model.addAttribute("user", userDTO);
            return "user";
        }
    }

    @PostAuthorize("isAuthenticated() and #username == authentication.principal.username")
    @GetMapping("/{name}/roles")
    @ResponseBody
    public String getRoles(@PathVariable("name") String username) {
        System.out.println("Called method \'getRoles\'");
        User byUsername = userService.findByUsername(username);
        System.out.println(byUsername.getRole().name());
        return byUsername.getRole().name();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You are not authorize");
        }

        User user = userService.findByUsername(principal.getName());

        UserDTO userDTO = UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        model.addAttribute("user", userDTO);

        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String updateProfileUser(UserDTO userDTO, Model model, Principal principal) {
        if (principal == null || !Objects.equals(principal.getName(), userDTO.getEmail())) {
            throw new RuntimeException("You are not authorize");
        }

        if (userDTO.getPassword() != null
                    && !userDTO.getPassword().isEmpty()
                    && !Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            model.addAttribute("user", userDTO);
            return "profile";
        }

        userService.updateProfile(userDTO);
        return "redirect:/users/profile";
    }

}
