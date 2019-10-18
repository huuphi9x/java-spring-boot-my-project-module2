package com.codegym.javamyprojectmodule2.service.impl;

import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.repository.NationalRepository;
import com.codegym.javamyprojectmodule2.service.NationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NationalServiceImpl implements NationalService {

    @Autowired
    NationalRepository nationalRepository;

    @Override
    public Page<National> findAll(Pageable pageable) {
        return nationalRepository.findAll(pageable);
    }

    @Override
    public National save(National national) {
        return nationalRepository.save(national);
    }

    @Override
    public Optional<National> findById(Long id) {
        return nationalRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        nationalRepository.deleteById(id);

    }

    @Override
    public Page<National> findByName(String name, Pageable pageable) {
        return nationalRepository.findAllByNameContains(name,pageable);
    }

    @Override
    public Iterable<National> findAll() {
        return nationalRepository.findAll();
    }
}
