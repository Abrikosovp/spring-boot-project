package ru.sweater.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sweater.model.Role;
import ru.sweater.model.User;
import ru.sweater.repository.UserRepos;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepos userRepos;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepos.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEdit(
            Model model,
            @PathVariable User user) {

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form,
            @RequestParam String username) {

        user.setUsername(username);

        Set<String> roles = Arrays.stream(
                Role.values())
                .map(Role::name)
                .collect(Collectors.toSet()
                );

        user.getRoles().clear();

        form.forEach((k, v) -> {
                    if (roles.contains(k)) {
                        user.getRoles().add(Role.valueOf(k));
                    }
                }
        );

        userRepos.save(user);
        return "redirect:/user";
    }
}
