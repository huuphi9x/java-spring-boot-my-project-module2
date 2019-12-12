package com.codegym.javamyprojectmodule2.controller;

import com.codegym.javamyprojectmodule2.model.Tournaments;
import com.codegym.javamyprojectmodule2.service.TournamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class APITournamentsController {

    @Autowired
    private TournamentsService tournamentsService;

    @GetMapping(value = "/api/tournaments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Tournaments> getTournaments(@PathVariable Long id) {
        Tournaments tournaments = tournamentsService.findById(id).get();
        if (tournaments == null) {
            return new ResponseEntity<Tournaments>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Tournaments>(tournaments, HttpStatus.OK);
    }

    @GetMapping(value = "/api/tournaments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Tournaments>> apiList() {
        Iterable<Tournaments> tournaments = tournamentsService.findAll();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    @DeleteMapping("api/delete-tournaments/{id}")
    @ResponseBody
    public ResponseEntity<Void> apiDeleteTournaments(@PathVariable Long id) {
        Optional<Tournaments> target = tournamentsService.findById(id);
        if (!target.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tournamentsService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/api/create-tournaments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> addTournaments(@RequestBody Tournaments tournaments, UriComponentsBuilder ucBuilder) {
        tournamentsService.save(tournaments);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/tournaments/{id}").buildAndExpand(tournaments.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/edit-tournaments/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tournaments> updateTournaments(@PathVariable("id") Long id,
                                                         @RequestBody Tournaments tournaments) {
        Optional<Tournaments> originTournaments = tournamentsService.findById(id);

        if (!originTournaments.isPresent()) {
            return new ResponseEntity<Tournaments>(HttpStatus.NOT_FOUND);
        }
        originTournaments.get().setName(tournaments.getName());
        tournamentsService.save(tournaments);
        return new ResponseEntity<Tournaments>(HttpStatus.OK);
    }
}
