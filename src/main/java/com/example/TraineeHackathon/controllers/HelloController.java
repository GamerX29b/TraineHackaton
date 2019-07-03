package com.example.TraineeHackathon.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping(value = "/")
    public ModelAndView hello () {
        ModelAndView mv = new ModelAndView("HelloPage");
        return mv;
    }
}
