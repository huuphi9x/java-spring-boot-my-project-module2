package com.codegym.javamyprojectmodule2.service.impl;

import com.codegym.javamyprojectmodule2.model.Tournaments;
import com.codegym.javamyprojectmodule2.repository.TournamentsRepository;
import com.codegym.javamyprojectmodule2.service.TournamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentsServiceImpl implements TournamentsService {

    @Autowired
    private TournamentsRepository tournamentsRepository;

    @Override
    public Page<Tournaments> findAll(Pageable pageable) {
        return tournamentsRepository.findAll(pageable);
    }

    @Override
    public Tournaments save(Tournaments tournaments) {
        return tournamentsRepository.save(tournaments);
    }

    @Override
    public Optional<Tournaments> findById(Long id) {
        return tournamentsRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        tournamentsRepository.deleteById(id);

    }

    @Override
    public Page<Tournaments> findByName(String name, Pageable pageable) {
        return tournamentsRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public List<Tournaments> findAll() {
        return (List<Tournaments>) tournamentsRepository.findAll();
    }

}
