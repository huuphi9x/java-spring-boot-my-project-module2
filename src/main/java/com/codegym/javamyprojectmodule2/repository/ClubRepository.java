package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Club;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClubRepository extends PagingAndSortingRepository<Club, Long> {
//    Club findOne(Long id);
}
