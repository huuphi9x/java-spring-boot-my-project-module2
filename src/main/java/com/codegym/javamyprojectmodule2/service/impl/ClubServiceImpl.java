package com.codegym.javamyprojectmodule2.service.impl;

import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.repository.ClubRepository;
import com.codegym.javamyprojectmodule2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public Page<Club> findAll(Pageable pageable) {
        return clubRepository.findAll(pageable);
    }

    @Override
    public Club save(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        clubRepository.deleteById(id);

    }

    @Override
    public Page<Club> findByName(String name, Pageable pageable) {
        return clubRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public Iterable<Club> findAll() {
        return clubRepository.findAll();
    }


}
