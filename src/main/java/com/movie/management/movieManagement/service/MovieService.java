package com.movie.management.movieManagement.service;

import com.movie.management.movieManagement.dto.MovieDto;

import java.util.List;

public interface MovieService {
    public void saveMovie(MovieDto movieDto);

    public List<MovieDto> getMovies();

    public MovieDto getMoviebyId(String id);

    public void editMovieDetails(String id, MovieDto movieDto);

    public void deleteMovie(String id);
}
