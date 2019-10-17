package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClubRepository extends PagingAndSortingRepository<Club, Long> {

    Page<Club> findAllByNameContains(String name, Pageable pageable);
}
