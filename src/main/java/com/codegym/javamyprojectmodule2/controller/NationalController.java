package com.codegym.javamyprojectmodule2.controller;

import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.model.Tournaments;
import com.codegym.javamyprojectmodule2.service.ClubService;
import com.codegym.javamyprojectmodule2.service.NationalService;
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
public class NationalController {
    @Autowired
    private NationalService nationalService;


        @GetMapping("/national")
        public ModelAndView showListNational(@PageableDefault(size = 5) Pageable pageable) {
            Page<National> nationals = nationalService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("national/list");
            modelAndView.addObject("nationals", nationals);
            return modelAndView;
        }


        @GetMapping("/create-national")
        public ModelAndView showCreateNational() {
            ModelAndView modelAndView = new ModelAndView("national/create");
            modelAndView.addObject("national", new National());
            return modelAndView;
        }

        @PostMapping("/create-national")
        public ModelAndView showSaveNational(@Valid @ModelAttribute("national") National national, BindingResult bindingResult) {
//            new ClubValidate().validate(club, bindingResult);
            if (bindingResult.hasFieldErrors()) {
                ModelAndView modelAndView = new ModelAndView("national/create");
                return modelAndView;
            } else {
                nationalService.save(national);
                ModelAndView modelAndView = new ModelAndView("national/create");
                modelAndView.addObject("name", national.getName());
                modelAndView.addObject("message", "New national created successfully");
                return modelAndView;
            }
        }

        @GetMapping("/edit-national/{id}")
        public ModelAndView showEditNational(@PathVariable Long id) {
            Optional<National> national = nationalService.findById(id);
            ModelAndView modelAndView = new ModelAndView("national/edit");
            modelAndView.addObject("national", national);
            return modelAndView;
        }

        @PostMapping("/edit-national")
        public ModelAndView updateNational(@ModelAttribute("national") National national) {
            nationalService.save(national);
            ModelAndView modelAndView = new ModelAndView("national/edit");
            modelAndView.addObject("national", national);
            modelAndView.addObject("message", "National updated successfully");
            return modelAndView;
        }

        @GetMapping("/delete-national/{id}")
        public ModelAndView showDeleteNational(@PathVariable Long id) {
            Optional<National> national = nationalService.findById(id);
            ModelAndView modelAndView = new ModelAndView("national/delete");
            modelAndView.addObject("national", national.get());
            return modelAndView;
        }

        @PostMapping("/delete-national")
        public ModelAndView deleteNational(@ModelAttribute("national") National national) {
            nationalService.remove(national.getId());
            ModelAndView modelAndView = new ModelAndView("redirect:/national");
            return modelAndView;
        }

        @GetMapping("/search-national")
        public ModelAndView showSearchNational(@RequestParam String name, RedirectAttributes redirectAttributes, @PageableDefault(size = 5) Pageable pageable) {
            Page<National> nationals = nationalService.findByName(name, pageable);
            if (nationals.getTotalElements() > 0) {
                ModelAndView modelAndView = new ModelAndView("national/search");
                modelAndView.addObject("nationals", nationals);
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("redirect:/national");
                redirectAttributes.addFlashAttribute("message", "Not found");
                return modelAndView;
            }
        }
    }
