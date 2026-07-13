package org.ood.moviebooking.application.port.out;

import org.ood.moviebooking.domain.entity.Show;

import java.util.Optional;

public interface ShowRepository {
    Optional<Show> findById(String showId);
}
