package com.movie.management.movieManagement.service;

import com.movie.management.movieManagement.dto.MovieDto;
import com.movie.management.movieManagement.entity.Movie;
import com.movie.management.movieManagement.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDirector(movieDto.getDirector());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setRating(movieDto.getRating());
        movieRepository.save(movie);
    }

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movies = movieRepository.findAll();
        List<MovieDto> movieDtos = new ArrayList<>();
        movieDtos = movies.stream().map(m -> modelMapper.map(m, MovieDto.class)).toList();
        return movieDtos;
    }

    @Override
    public MovieDto getMoviebyId(String id) {
        Movie movie = movieRepository.findMovieById(id);
        
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        return movieDto;
    }

    @Override
    public void editMovieDetails(String id, MovieDto movieDto) {
        Movie movie = movieRepository.findMovieById(id);
        if (movieDto.getTitle() != null && !movieDto.getTitle().isEmpty()) {
            movie.setTitle(movieDto.getTitle());
        }
        if (movieDto.getRating() != 0) {
            movie.setRating(movieDto.getRating());
        }
        if (movieDto.getDirector() != null && !movieDto.getDirector().isEmpty()) {
            movie.setDirector(movieDto.getDirector());
        }
        if (movieDto.getGenre() != null && !movieDto.getGenre().isEmpty()) {
            movie.setGenre(movieDto.getGenre());
        }
        if (movieDto.getReleaseYear() != 0) {
            movie.setReleaseYear(movieDto.getReleaseYear());
        }
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(String id) {
        Movie movie = movieRepository.findMovieById(id);
        movieRepository.delete(movie);
    }
}