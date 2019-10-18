package com.codegym.javamyprojectmodule2.service;

import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.model.Player;
import com.codegym.javamyprojectmodule2.model.Position;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface PositionService {
    Iterable<Position> findAll();


    Optional<Position> findById(Long id);
}
