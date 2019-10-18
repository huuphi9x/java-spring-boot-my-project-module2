package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    Page<Player> findAllByNameContains(String name, Pageable pageable);

    Page<Player> findAllByClubId(Long clubId,Pageable pageable);
}
