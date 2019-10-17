package com.codegym.javamyprojectmodule2.service;

import com.codegym.javamyprojectmodule2.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ClubService {
    Page<Club> findAll(Pageable pageable);

    Club save(Club club);

    Optional<Club> findById(Long id);

    void remove(Long id);

    Page<Club> findByName(String name, Pageable pageable);

    Iterable<Club> findAll();


}
