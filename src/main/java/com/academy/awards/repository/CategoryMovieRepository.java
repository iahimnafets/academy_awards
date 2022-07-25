package com.academy.awards.repository;

import com.academy.awards.entitys.CategoryMovie;
import com.academy.awards.entitys.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMovieRepository extends JpaRepository<CategoryMovie, Long> {

    List<CategoryMovie> findByDescription(String description);
}
