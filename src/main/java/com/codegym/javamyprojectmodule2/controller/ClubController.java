package com.codegym.javamyprojectmodule2.controller;


import com.codegym.javamyprojectmodule2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public ModelAndView showListClub(){
        ModelAndView modelAndView = new ModelAndView("list");
        return modelAndView;
    }
}
