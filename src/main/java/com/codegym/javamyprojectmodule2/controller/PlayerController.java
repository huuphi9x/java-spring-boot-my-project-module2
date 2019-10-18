package com.codegym.javamyprojectmodule2.controller;


import com.codegym.javamyprojectmodule2.formatter.PlayerFormatter;
import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.model.Player;
import com.codegym.javamyprojectmodule2.model.Position;
import com.codegym.javamyprojectmodule2.service.ClubService;
import com.codegym.javamyprojectmodule2.service.NationalService;
import com.codegym.javamyprojectmodule2.service.PlayerService;
import com.codegym.javamyprojectmodule2.service.PositionService;
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
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ClubService clubService;


    @Autowired
    private NationalService nationalService;

    @Autowired
    private PositionService positionService;

    @ModelAttribute("position")
    public Iterable<Position> positions() {
        return positionService.findAll();
    }

    @ModelAttribute("national")
    public Iterable<National> nationals() {
        return nationalService.findAll();
    }

    @ModelAttribute("club")
    public Iterable<Club> clubs() {
        return clubService.findAll();
    }

    @GetMapping("/player")
    public ModelAndView showListPlayer(@PageableDefault(size = 5) Pageable pageable) {
        Page<Player> players = playerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("player/list");
        for(Player list : players){
            System.out.println(list.getClub().getName());
        }
        modelAndView.addObject("players", players);
        return modelAndView;
    }


    @GetMapping("/create-player")
    public ModelAndView showCreatePlayer() {
        ModelAndView modelAndView = new ModelAndView("player/create");
        modelAndView.addObject("player", new Player());
        return modelAndView;
    }

    @PostMapping("/create-player")
    public ModelAndView showSavePlayer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult) {
//        new ClubValidate().validate(player, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("player/create");
            return modelAndView;
        } else {
            playerService.save(player);
            ModelAndView modelAndView = new ModelAndView("player/create");
            modelAndView.addObject("name", player.getName());
            modelAndView.addObject("age", player.getAge());
            modelAndView.addObject("position", player.getPosition());
            modelAndView.addObject("club", player.getClub());
            modelAndView.addObject("national", player.getNational());
            modelAndView.addObject("message", "New player created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-player/{id}")
    public ModelAndView showEditPlayer(@PathVariable Long id) {
        Optional<Player> player = playerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("player/edit");
        modelAndView.addObject("player", player);
        return modelAndView;
    }

    @PostMapping("/edit-player")
    public ModelAndView updatePlayer(@ModelAttribute("player") Player player) {
        playerService.save(player);
        ModelAndView modelAndView = new ModelAndView("player/edit");
        modelAndView.addObject("player", player);
        modelAndView.addObject("message", "Player updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-player/{id}")
    public ModelAndView showDeletePlayer(@PathVariable Long id) {
        Optional<Player> player = playerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("player/delete");
        modelAndView.addObject("player", player.get());
        return modelAndView;
    }

    @PostMapping("/delete-player")
    public ModelAndView deletePlayer(@ModelAttribute("player") Player player) {
        playerService.remove(player.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/player");
        return modelAndView;
    }

    @GetMapping("/search-player")
    public ModelAndView showSearchPlayer(@RequestParam String name, RedirectAttributes redirectAttributes, @PageableDefault(size = 5) Pageable pageable) {
        Page<Player> playerList = playerService.findByName(name, pageable);
        if (playerList.getTotalElements() != 0) {
            ModelAndView modelAndView = new ModelAndView("player/search");
            modelAndView.addObject("playerList", playerList);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/player");
            redirectAttributes.addFlashAttribute("message", "Not found");
            return modelAndView;
        }

    }
}