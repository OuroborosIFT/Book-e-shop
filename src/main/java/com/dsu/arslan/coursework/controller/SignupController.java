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
@RequestMapping({"/signup", "/signup.html"})
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signup(Model model) {  // аналог - newUser()
        System.out.println("Called method \'newUser\'");
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @PostMapping("/make")
    public String save(UserDTO userDTO, Model model) { // аналог saveUser()
        System.out.println("Called method \'save\'");

        if (userService.save(userDTO)) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", userDTO);
            return "signup";
        }
    }

}
