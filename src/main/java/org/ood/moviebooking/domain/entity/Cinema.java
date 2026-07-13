package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    @Getter
    private final String cinemaId;
    private final String name;
    @Getter
    private final String city;
    private final List<CinemaHall> halls = new ArrayList<>();

    public Cinema(String cinemaId, String name, String city) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.city = city;
    }

    public void addHall(CinemaHall hall) {
        halls.add(hall);
    }

    public List<CinemaHall> getHalls() {
        return List.copyOf(halls);
    }
}
