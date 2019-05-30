package ru.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sweater.model.Role;
import ru.sweater.model.User;
import ru.sweater.repository.UserRepos;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepos repos;

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView modelAndView) {
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(
            ModelAndView model,
            User user
    ) {
        System.out.println(user.toString());
        User username = repos.findByUsername(user.getUsername());
        if (username != null) {
            model.addObject("message", "Not exist");
            model.setViewName("registration");
        } else {
            model.setViewName("login");
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.ADMIN));
            repos.save(user);

        }
        return model;
    }
}
