package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CinemaHall {

    @Getter
    private final String hallId;
    private final String name;
    private final List<Seat> seats = new ArrayList<>();
    private final List<Show> shows = new ArrayList<>();

    public CinemaHall(String hallId, String name) {
        this.hallId = hallId;
        this.name = name;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<Seat> getSeats() {
        return List.copyOf(seats);
    }

    public List<Show> getShows() {
        return List.copyOf(shows);
    }
}
