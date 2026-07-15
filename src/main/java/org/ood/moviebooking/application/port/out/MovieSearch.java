package org.ood.moviebooking.application.port.out;

import org.ood.moviebooking.domain.entity.Movie;

import java.util.List;

public interface MovieSearch {
    List<Movie> searchByTitle(String title);

    List<Movie> searchByGenre(String genre);

    List<Movie> searchByLanguage(String language);
}
