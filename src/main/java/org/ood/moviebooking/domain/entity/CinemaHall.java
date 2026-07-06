package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CinemaHall {
    private final String hallId;
    private final String name;
    private final List<Seat> seats = new ArrayList<>();
    private final List<Show> shows = new ArrayList<>();

    public CinemaHall(String hallId, String name) {
        this.hallId = hallId;
        this.name = name;
    }
}
