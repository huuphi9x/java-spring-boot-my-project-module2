package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    List<Player> findAllByNameContains(String name);
}
