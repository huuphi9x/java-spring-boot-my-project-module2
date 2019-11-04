package com.codegym.javamyprojectmodule2.controller;

import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.service.NationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.stream.Location;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class APINationController {

    @Autowired
    private NationalService nationalService;



    @GetMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<National> getNational(@PathVariable Long id){
        National national= nationalService.findById(id).get();
        if(national == null){
            return new ResponseEntity<National>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<National>(national,HttpStatus.OK);
    }

    @GetMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<National>>apiList() {
        Iterable<National> nationals = nationalService.findAll();
        return new ResponseEntity<>(nationals, HttpStatus.OK);
    }

    @DeleteMapping("api/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> apiDeleteNational(@PathVariable Long id) {
        Optional<National> target = nationalService.findById(id);
        if (target == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nationalService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/api/create-national" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> addNational(@RequestBody National national, UriComponentsBuilder ucBuilder){
       nationalService.save(national);
       HttpHeaders headers =new HttpHeaders();
       headers.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(national.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/edit-national/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<National> updateNational(@PathVariable("id") long id,
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
