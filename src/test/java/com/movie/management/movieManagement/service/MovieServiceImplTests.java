package com.movie.management.movieManagement.service;

import com.movie.management.movieManagement.dto.MovieDto;
import com.movie.management.movieManagement.entity.Movie;
import com.movie.management.movieManagement.exception.MovieNotFoundException;
import com.movie.management.movieManagement.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService; // your service implementation

    private Movie sampleMovie;

    @BeforeEach
    void setUp() {
        sampleMovie = new Movie();
        sampleMovie.setId("1");
        sampleMovie.setTitle("The Shawshank Redemption");
        sampleMovie.setDirector("Frank Darabont");
        sampleMovie.setReleaseYear(1994);
        sampleMovie.setGenre("Drama");
        sampleMovie.setRating(9);
    }

    @Test
    void saveMovie_shouldSaveAndReturnSaved() {
        when(movieRepository.save(any(Movie.class))).thenAnswer(i -> i.getArgument(0));

        MovieDto dto = new MovieDto();
        dto.setTitle(sampleMovie.getTitle());
        dto.setDirector(sampleMovie.getDirector());
        dto.setReleaseYear(sampleMovie.getReleaseYear());
        dto.setGenre(sampleMovie.getGenre());
        dto.setRating(sampleMovie.getRating());

        movieService.saveMovie(dto);

        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void getAllMovies_shouldReturnList() {
        when(movieRepository.findAll()).thenReturn(List.of(sampleMovie));

        List<MovieDto> moviesDto = movieService.getMovies();

        assertNotNull(moviesDto);
        assertEquals(1, moviesDto.size());
        assertEquals("The Shawshank Redemption", moviesDto.get(0).getTitle());
    }

    @Test
    void getMovieById_whenFound_shouldReturn() {
        when(movieRepository.existsById("1")).thenReturn(true);
        when(movieRepository.findMovieById("1")).thenReturn(sampleMovie);
        MovieDto movieDto = movieService.getMoviebyId("1");
        assertNotNull(movieDto);
        assertEquals("1", movieDto.getId());
    }

    @Test
    void getMovieById_whenNotFound_shouldThrow() {
        when(movieRepository.existsById("2")).thenReturn(false);
        assertThrows(MovieNotFoundException.class, () -> movieService.getMoviebyId("2"));
        verify(movieRepository, never()).findMovieById(anyString());
    }

    @Test
    void editMovieDetails_whenExists_shouldUpdate() {
        when(movieRepository.existsById("1")).thenReturn(true);
        when(movieRepository.findMovieById("1")).thenReturn(sampleMovie);
        when(movieRepository.save(any(Movie.class))).thenAnswer(i -> i.getArgument(0));

        MovieDto dto = new MovieDto();
        dto.setTitle("Updated Title");
        dto.setRating(10);

        movieService.editMovieDetails("1", dto);

        verify(movieRepository).save(argThat(m -> m.getTitle().equals("Updated Title") && m.getRating() == 10));
    }

    @Test
    void deleteMovie_whenExists_shouldDelete() {
        when(movieRepository.existsById("1")).thenReturn(true);

        movieService.deleteMovie("1");

        verify(movieRepository, times(1)).deleteById("1");
    }

    @Test
    void deleteMovie_whenNotExists_shouldThrow() {
        when(movieRepository.existsById("2")).thenReturn(false);

        assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie("2"));
        verify(movieRepository, never()).deleteById(anyString());
    }
}
