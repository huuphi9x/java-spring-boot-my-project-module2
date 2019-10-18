package com.codegym.javamyprojectmodule2.repository;

import com.codegym.javamyprojectmodule2.model.Position;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PositionRepository extends PagingAndSortingRepository<Position,Long> {
}
