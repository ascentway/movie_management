package com.movie.management.movieManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.management.movieManagement.dto.MovieDto;
import com.movie.management.movieManagement.entity.Movie;
import com.movie.management.movieManagement.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMovies_shouldReturnOkAndJson() throws Exception {
        MovieDto m = new MovieDto();
        m.setId("1");
        m.setTitle("Inception");

        Mockito.when(movieService.getMovies()).thenReturn(List.of(m));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Inception"));
    }

    @Test
    void getMovieById_whenFound_shouldReturnOk() throws Exception {
        MovieDto m = new MovieDto();
        m.setId("1");
        m.setTitle("Interstellar");

        Mockito.when(movieService.getMoviebyId("1")).thenReturn(m);

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.title").value("Interstellar"));
    }

    @Test
    void createMovie_shouldReturnCreated() throws Exception {
        MovieDto dto = new MovieDto();
        dto.setTitle("DJ");
        dto.setDirector("Nolan");
        dto.setReleaseYear(2017);

       Mockito.when(movieService.saveMovie(any(MovieDto.class))).thenReturn("Movie Details Saved Successfully.");

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateMovie_shouldReturnOk() throws Exception {
        MovieDto dto = new MovieDto();
        dto.setTitle("Updated");

        Mockito.doNothing().when(movieService).editMovieDetails(eq("1"), any(MovieDto.class));

        mockMvc.perform(put("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isAccepted());
    }

    @Test
    void deleteMovie_shouldReturnNoContent() throws Exception {
        Mockito.doNothing().when(movieService).deleteMovie("1");

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isOk());
    }
}
