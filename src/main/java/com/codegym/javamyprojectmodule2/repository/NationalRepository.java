package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.model.National;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NationalRepository extends PagingAndSortingRepository<National,Long> {
    Page<National> findAllByNameContains(String name, Pageable pageable);
}
