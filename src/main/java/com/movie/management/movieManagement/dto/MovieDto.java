package com.movie.management.movieManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto {
    String id;
    String title;
    String director;
    int releaseYear;
    String genre;
    int rating;
}
