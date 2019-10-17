package com.codegym.javamyprojectmodule2.service.impl;

import com.codegym.javamyprojectmodule2.model.Player;
import com.codegym.javamyprojectmodule2.repository.PlayerRepository;
import com.codegym.javamyprojectmodule2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        playerRepository.deleteById(id);

    }

    @Override
    public List<Player> findByName(String name) {
        return playerRepository.findAllByNameContains(name);
    }

}
