package com.movie.management.movieManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    String director;
    int releaseYear;
    String genre;
    int rating;

    public Movie(String director, String title, int releaseYear, String genre, int rating) {
        this.director = director;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }

    public Movie() {
      super();
    }
}
