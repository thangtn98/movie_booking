package org.ood.moviebooking.domain.entity;


import lombok.Getter;

@Getter
public abstract class User {
    Long id;
    String name;
    String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
