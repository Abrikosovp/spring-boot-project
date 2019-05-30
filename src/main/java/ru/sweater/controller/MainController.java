package ru.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.sweater.model.Message;
import ru.sweater.model.User;
import ru.sweater.repository.MessageRepos;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MessageRepos repos;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public ModelAndView main(
            ModelAndView model,
            @RequestParam(name = "filter", required = false) String filter
    ) {
        List<Message> result;
        if (filter != null && !filter.isEmpty()) {
            result = repos.findByTag(filter);
        } else {
            result = repos.findAll();
        }
        model.setViewName("main");
        model.addObject("messages", result);
        return model;
    }


    @PostMapping("/main")
    public ModelAndView addMessage(
            @AuthenticationPrincipal User user,
            Message message,
            ModelAndView model
    ) {
        model.setViewName("main");
        message.setAuthor(user);
        repos.save(message);
        model.addObject("messages", repos.findAll());
        return model;
    }
}
