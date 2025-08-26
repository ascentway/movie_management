package com.movie.management.movieManagement.controller;

import com.movie.management.movieManagement.dto.MovieDto;
import com.movie.management.movieManagement.exception.NoMovieFoundException;
import com.movie.management.movieManagement.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<String> saveMovieDetails(@RequestBody MovieDto movieDto) {
        System.out.println(movieDto.toString());
        String message = movieService.saveMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() throws NoMovieFoundException {
        List<MovieDto> movies = movieService.getMovies();
        return ResponseEntity.status(HttpStatus.FOUND).body(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id) {
        MovieDto movieDto = movieService.getMoviebyId(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(movieDto);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<String> editMovieDetails(@PathVariable String id, @RequestBody MovieDto movieDto) {
        movieService.editMovieDetails(id, movieDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Movie Details Updated Successfully.");
    }

    @DeleteMapping("movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body("Movie has been deleted successfully.");
    }
}
