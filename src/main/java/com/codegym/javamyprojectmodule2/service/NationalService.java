package com.codegym.javamyprojectmodule2.service;

import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.model.National;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NationalService {
    Page<National> findAll(Pageable pageable);

    National save(National national);

    Optional<National> findById(Long id);

    void remove(Long id);

    Page<National> findByName(String name, Pageable pageable);

    Iterable<National> findAll();
}
