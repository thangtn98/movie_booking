package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class Cinema {
    private final String cinemaId;
    private final String name;
    private final String city;
    private final List<CinemaHall> halls;

    public Cinema(String cinemaId, String name, String city, List<CinemaHall> halls) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.city = city;
        this.halls = halls;
    }
}
