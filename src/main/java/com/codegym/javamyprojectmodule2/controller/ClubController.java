package com.codegym.javamyprojectmodule2.controller;


import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.service.ClubService;
import com.codegym.javamyprojectmodule2.validate.ClubValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public ModelAndView showListClub(@PageableDefault(size = 5)Pageable pageable){
        Page<Club> clubs = clubService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("clubs",clubs);
        return modelAndView;
    }


    @GetMapping("/create-club")
    public ModelAndView showCreateClub(){
    ModelAndView modelAndView =new ModelAndView("create");
    modelAndView.addObject("club",new Club());
    return modelAndView;
    }

    @PostMapping("/create-club")
    public ModelAndView showSaveClub(@Valid @ModelAttribute("club")Club club, BindingResult bindingResult){
        new ClubValidate().validate(club,bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        } else {
            clubService.save(club);
            ModelAndView modelAndView =new ModelAndView("create");
            modelAndView.addObject("name",club.getName());
            modelAndView.addObject("stadium",club.getStadium());
            modelAndView.addObject("tournaments",club.getTournaments());
            modelAndView.addObject("national",club.getNational());
            modelAndView.addObject("message","New club created successfully");
            return modelAndView;
        }
    }

}
