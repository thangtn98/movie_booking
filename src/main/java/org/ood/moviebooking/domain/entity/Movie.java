package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.time.Duration;

@Getter
public class Movie {
    private final String movieId;
    private final String title;
    private final String language;
    private final String genre;
    private final Duration runtime;

    public Movie(String movieId, String title, String language, String genre, Duration runtime) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.runtime = runtime;
    }
}
