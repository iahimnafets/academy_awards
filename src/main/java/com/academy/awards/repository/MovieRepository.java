package com.academy.awards.repository;

import com.academy.awards.entitys.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie order by rate desc limit :limit", nativeQuery = true)
    public List<Movie> findByTopRate(@Param("limit") int limit);

    public List<Movie> findByNominee(String nominee);

}
