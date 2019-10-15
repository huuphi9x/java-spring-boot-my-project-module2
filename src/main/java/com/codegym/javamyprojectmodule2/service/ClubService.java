package com.codegym.javamyprojectmodule2.service;

import com.codegym.javamyprojectmodule2.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ClubService {
    Page<Club> findAll(Pageable pageable);

    Club save(Club club);

//    Club findById(Long id);



}
