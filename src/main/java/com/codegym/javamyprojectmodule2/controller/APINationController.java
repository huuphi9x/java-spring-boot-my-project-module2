package com.codegym.javamyprojectmodule2.controller;

import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.service.NationalService;
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
public class APINationController {

    @Autowired
    private NationalService nationalService;

    @GetMapping(value = "/api/national/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<National> getNational(@PathVariable Long id){
        National national= nationalService.findById(id).get();
        if(national == null){
            return new ResponseEntity<National>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<National>(national,HttpStatus.OK);
    }

    @GetMapping(value = "/api/national", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<National>>apiList() {
        Iterable<National> nationals = nationalService.findAll();
        return new ResponseEntity<>(nationals, HttpStatus.OK);
    }

    @DeleteMapping("api/delete-national/{id}")
    public ResponseEntity<Void> apiDeleteNational(@PathVariable Long id) {
        Optional<National> target = nationalService.findById(id);
        if (!target.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nationalService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/api/create-national" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNational(@RequestBody National national, UriComponentsBuilder ucBuilder){
       nationalService.save(national);
       HttpHeaders headers =new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/national/{id}").buildAndExpand(national.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/edit-national/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<National> updateNational(@PathVariable("id") Long id,
                                                   @RequestBody National national) {
        Optional<National> originNational = nationalService.findById(id);

        if (!originNational.isPresent()) {
            return new ResponseEntity<National>(HttpStatus.NOT_FOUND);
        }
        originNational.get().setName(national.getName());
        nationalService.save(national);
        return new ResponseEntity<National>(HttpStatus.OK);
    }




}
