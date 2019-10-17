package com.codegym.javamyprojectmodule2.service.impl;

import com.codegym.javamyprojectmodule2.repository.PlayerRepository;
import com.codegym.javamyprojectmodule2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;
}
