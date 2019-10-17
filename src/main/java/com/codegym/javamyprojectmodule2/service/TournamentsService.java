package com.codegym.javamyprojectmodule2.service;

import com.codegym.javamyprojectmodule2.model.Tournaments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TournamentsService {

    Page<Tournaments> findAll(Pageable pageable);

    Tournaments save(Tournaments tournaments);

    Optional<Tournaments> findById(Long id);

    void remove(Long id);

    Page<Tournaments> findByName(String name, Pageable pageable);

    Iterable<Tournaments> findAll();

}
