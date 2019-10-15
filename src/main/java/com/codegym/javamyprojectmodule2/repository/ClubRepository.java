package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Club;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClubRepository extends PagingAndSortingRepository<Club, Long> {
}
