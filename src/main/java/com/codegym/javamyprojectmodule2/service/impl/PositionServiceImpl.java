package com.codegym.javamyprojectmodule2.service.impl;

import com.codegym.javamyprojectmodule2.model.Position;
import com.codegym.javamyprojectmodule2.repository.PositionRepository;
import com.codegym.javamyprojectmodule2.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Iterable<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }
}
