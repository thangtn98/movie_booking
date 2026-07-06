package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Show {
    private final String showId;
    private final Movie movie;
    private final CinemaHall hall;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Map<String, ShowSeat> showSeats = new HashMap<>(); // seatId -> ShowSeat

    public Show(String showId, Movie movie, CinemaHall hall, LocalDateTime startTime, LocalDateTime endTime) {
        this.showId = showId;
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addShowSeat(ShowSeat showSeat) {
        showSeats.put(showSeat.getSeat().getSeatId(), showSeat);
    }

    public ShowSeat getShowSeat(String seatId) {
        return showSeats.get(seatId);
    }

    public Map<String, ShowSeat> getShowSeats() {
        return Map.copyOf(showSeats);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Show show)) return false;
        return Objects.equals(showId, show.showId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(showId);
    }
}
