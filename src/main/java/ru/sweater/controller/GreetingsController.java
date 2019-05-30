package ru.sweater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingsController {

    @GetMapping("/greeting")
    public ModelAndView greeting(
            @RequestParam(name = "name", required = false, defaultValue = "expected") String name,
            ModelAndView model
    ) {
        model.setViewName("greeting");
        model.addObject("name", name);
        return model;
    }
}
