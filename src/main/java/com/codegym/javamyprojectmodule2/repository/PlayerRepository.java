package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
}
