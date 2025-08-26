package com.movie.management.movieManagement.service;

import com.movie.management.movieManagement.dto.MovieDto;
import com.movie.management.movieManagement.entity.Movie;
import com.movie.management.movieManagement.exception.MovieNotFoundException;
import com.movie.management.movieManagement.exception.NoMovieFoundException;
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
    private final MovieRepository movieRepository;

    @Override
    public String saveMovie(MovieDto movieDto) {
        try {

            Movie movie = new Movie();
            movie.setTitle(movieDto.getTitle());
            movie.setDirector(movieDto.getDirector());
            movie.setGenre(movieDto.getGenre());
            movie.setReleaseYear(movieDto.getReleaseYear());
            movie.setRating(movieDto.getRating());
            movieRepository.save(movie);
        } catch (Exception e) {
            throw new RuntimeException("Failed to Add Movie Details"+ e.getMessage());
        }
            return "Movie Details Saved Successfully.";
    }

    @Override
    public List<MovieDto> getMovies() {
            List<Movie> movies = new ArrayList<>();
            movies = movieRepository.findAll();
            if(movies.isEmpty()){
                throw new NoMovieFoundException("No movies found.");
            }
            try {
            List<MovieDto> movieDtos = new ArrayList<>();
            movieDtos = movies.stream().map(movie -> {
                MovieDto dto = new MovieDto();
                dto.setId(movie.getId());
                dto.setTitle(movie.getTitle());
                dto.setDirector(movie.getDirector());
                dto.setReleaseYear(movie.getReleaseYear());
                dto.setGenre(movie.getGenre());
                dto.setRating(movie.getRating());
                return dto;
            }).toList();
            return movieDtos;
        }catch (Exception e){
            throw new RuntimeException("Failed to Retrieve the data." +e.getMessage());
        }
    }

    @Override
    public MovieDto getMoviebyId(String id) {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException("No Movie Exists With Given ID.");
        }
        try {
            Movie movie = movieRepository.findMovieById(id);
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDto.setTitle(movie.getTitle());
            movieDto.setDirector(movie.getDirector());
            movieDto.setReleaseYear(movie.getReleaseYear());
            movieDto.setGenre(movie.getGenre());
            movieDto.setRating(movie.getRating());
            return movieDto;
        } catch (Exception e){
            throw new RuntimeException("No Movie Exists With Given ID." +e.getMessage());
        }
    }

    @Override
    public void editMovieDetails(String id, MovieDto movieDto) {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException("No Movie Exists With Given ID.");
        }
        try {
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
        }catch (Exception e){
            throw new RuntimeException("Failed to update movie details."+ e.getMessage());
        }
    }

    @Override
    public void deleteMovie(String id) {
            if(!(movieRepository.existsById(id))) {
                throw new MovieNotFoundException("No Movie Exists With Given ID.");
            }
        try{
            movieRepository.deleteById(id);
        } catch  (Exception e){
            throw new RuntimeException("Failed to delete movie details." + e.getMessage());
        }
    }
}