package com.codegym.javamyprojectmodule2.controller;


import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.model.Player;
import com.codegym.javamyprojectmodule2.model.Tournaments;
import com.codegym.javamyprojectmodule2.service.ClubService;
import com.codegym.javamyprojectmodule2.service.NationalService;
import com.codegym.javamyprojectmodule2.service.PlayerService;
import com.codegym.javamyprojectmodule2.service.TournamentsService;
import com.codegym.javamyprojectmodule2.validate.ClubValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TournamentsService tournamentsService;

    @Autowired
    private NationalService nationalService;

    @ModelAttribute("national")
    public Iterable<National> nationals() {
        return nationalService.findAll();
    }

    @ModelAttribute("tournaments")
    public Iterable<Tournaments> tournaments() {
        return tournamentsService.findAll();
    }
    @GetMapping("/club")
    public ModelAndView showListClub(@PageableDefault(size = 5) Pageable pageable) {
        Page<Club> clubs = clubService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("club/list");
        modelAndView.addObject("clubs", clubs);
        return modelAndView;
    }


    @GetMapping("/create-club")
    public ModelAndView showCreateClub() {
        ModelAndView modelAndView = new ModelAndView("club/create");
        modelAndView.addObject("club", new Club());
        return modelAndView;
    }

    @PostMapping("/create-club")
    public ModelAndView showSaveClub(@Valid @ModelAttribute("club") Club club, BindingResult bindingResult) {
        new ClubValidate().validate(club, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("club/create");
            return modelAndView;
        } else {
            clubService.save(club);
            ModelAndView modelAndView = new ModelAndView("club/create");
            modelAndView.addObject("name", club.getName());
            modelAndView.addObject("stadium", club.getStadium());
            modelAndView.addObject("tournaments", club.getTournaments());
            modelAndView.addObject("national", club.getNational());
            modelAndView.addObject("message", "New club created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-club/{id}")
    public ModelAndView showEditClub(@PathVariable Long id) {
        Optional<Club> club = clubService.findById(id);
        ModelAndView modelAndView = new ModelAndView("club/edit");
        modelAndView.addObject("club", club);
            return modelAndView;
    }

    @PostMapping("/edit-club")
    public ModelAndView updateClub(@ModelAttribute("club") Club club) {
        clubService.save(club);
        ModelAndView modelAndView = new ModelAndView("club/edit");
        modelAndView.addObject("club", club);
        modelAndView.addObject("message", "Club updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-club/{id}")
    public ModelAndView showDeleteClub(@PathVariable Long id) {
        Optional<Club> club = clubService.findById(id);
        ModelAndView modelAndView = new ModelAndView("club/delete");
        modelAndView.addObject("club", club.get());
        return modelAndView;
    }

    @PostMapping("/delete-club")
    public ModelAndView deleteClub(@ModelAttribute("club") Club club) {
        clubService.remove(club.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/club");
        return modelAndView;
    }

    @GetMapping("/search-club")
    public ModelAndView showSearchClub(@RequestParam String name, RedirectAttributes redirectAttributes, @PageableDefault(size = 5) Pageable pageable) {
        Page<Club> clubList = clubService.findByName(name, pageable);
        if (clubList.getTotalElements() > 0) {
            ModelAndView modelAndView = new ModelAndView("club/search");
            modelAndView.addObject("clubList", clubList);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/club");
            redirectAttributes.addFlashAttribute("message", "Not found");
            return modelAndView;
        }
    }

    @GetMapping("/detail-club/{id}")
    public ModelAndView showDetailClub(@PathVariable Long id, @PageableDefault(size = 5) Pageable pageable){
        Club club=clubService.findById(id).get();
        Page<Player> players = playerService.findByClubId(club.getId(), pageable);
        ModelAndView modelAndView= new ModelAndView("club/detail");
        modelAndView.addObject("club",players);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView showHome(){
        ModelAndView modelAndView = new ModelAndView("/home");
        return modelAndView;
    }

    @GetMapping("/contact")
    public ModelAndView showContact() {
        ModelAndView modelAndView = new ModelAndView("/contact");
        return modelAndView;
    }
}
