package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Tournaments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentsRepository extends PagingAndSortingRepository<Tournaments, Long> {
    Page<Tournaments> findAllByNameContains(String name, Pageable pageable);
}
