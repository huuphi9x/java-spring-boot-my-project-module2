package com.codegym.javamyprojectmodule2.controller;

import com.codegym.javamyprojectmodule2.model.Tournaments;
import com.codegym.javamyprojectmodule2.service.TournamentsService;
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
public class TournamentsController {


    @Autowired
    private TournamentsService tournamentsService;

    @GetMapping("/tournaments")
    public ModelAndView showListTournament(@PageableDefault(size = 5) Pageable pageable) {
        Page<Tournaments> tournaments = tournamentsService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("tournaments/list");
        modelAndView.addObject("tournaments", tournaments);
        return modelAndView;
    }


    @GetMapping("/create-tournaments")
    public ModelAndView showCreateTournaments() {
        ModelAndView modelAndView = new ModelAndView("tournaments/create");
        modelAndView.addObject("tournaments", new Tournaments());
        return modelAndView;
    }

    @PostMapping("/create-tournaments")
    public ModelAndView showSaveTournaments(@Valid @ModelAttribute("tournaments") Tournaments tournaments, BindingResult bindingResult) {
//        new ClubValidate().validate(tournaments, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("tournaments/create");
            return modelAndView;
        } else {
            tournamentsService.save(tournaments);
            ModelAndView modelAndView = new ModelAndView("tournaments/create");
            modelAndView.addObject("name", tournaments.getName());
            modelAndView.addObject("national", tournaments.getNational());
            modelAndView.addObject("message", "New tournaments created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-tournaments/{id}")
    public ModelAndView showEditTournaments(@PathVariable Long id) {
        Optional<Tournaments> tournaments = tournamentsService.findById(id);
        ModelAndView modelAndView = new ModelAndView("tournaments/edit");
        modelAndView.addObject("tournaments", tournaments);
        return modelAndView;
    }

    @PostMapping("/edit-tournaments")
    public ModelAndView updateTournaments(@ModelAttribute("tournaments") Tournaments tournaments) {
        tournamentsService.save(tournaments);
        ModelAndView modelAndView = new ModelAndView("tournaments/edit");
        modelAndView.addObject("tournaments", tournaments);
        modelAndView.addObject("message", "Tournaments updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-tournaments/{id}")
    public ModelAndView showDeleteTournaments(@PathVariable Long id) {
        Optional<Tournaments> tournaments = tournamentsService.findById(id);
        ModelAndView modelAndView = new ModelAndView("tournaments/delete");
        modelAndView.addObject("tournaments", tournaments.get());
        return modelAndView;
    }

    @PostMapping("/delete-tournaments")
    public ModelAndView deleteTournaments(@ModelAttribute("tournaments") Tournaments tournaments) {
        tournamentsService.remove(tournaments.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/tournaments");
        return modelAndView;
    }

    @GetMapping("/search-tournaments")
    public ModelAndView showSearchTournaments(@RequestParam String name, RedirectAttributes redirectAttributes, @PageableDefault(size = 5) Pageable pageable) {
        Page<Tournaments> tournaments = tournamentsService.findByName(name, pageable);
        if (tournaments.getTotalElements() != 0) {
            ModelAndView modelAndView = new ModelAndView("tournaments/search");
            modelAndView.addObject("tournaments", tournaments);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/tournaments");
            redirectAttributes.addFlashAttribute("message", "Not found");
            return modelAndView;
        }
    }
}

