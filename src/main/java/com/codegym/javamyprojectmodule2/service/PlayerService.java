package com.codegym.javamyprojectmodule2.service;

import com.codegym.javamyprojectmodule2.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PlayerService {

    Page<Player> findAll(Pageable pageable);

    Player save(Player player);

    Optional<Player> findById(Long id);

    void remove(Long id);

    Page<Player> findByName(String name, Pageable pageable);

}
