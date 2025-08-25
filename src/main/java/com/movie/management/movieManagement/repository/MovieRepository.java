package com.movie.management.movieManagement.repository;

import com.movie.management.movieManagement.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findMovieById(String id);
}
