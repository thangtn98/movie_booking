package org.ood.moviebooking.infrastructure.adapter.persistence;

import org.ood.moviebooking.application.port.out.ShowRepository;
import org.ood.moviebooking.domain.entity.Show;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryShowRepository implements ShowRepository {
    private final Map<String, Show> store = new ConcurrentHashMap<>();

    @Override
    public void save(Show show) {
        store.put(show.getShowId(), show);
    }

    @Override
    public Optional<Show> findById(String showId) {
        return Optional.ofNullable(store.get(showId));
    }
}
