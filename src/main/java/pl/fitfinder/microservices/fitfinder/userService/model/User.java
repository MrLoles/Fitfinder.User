package pl.fitfinder.microservices.fitfinder.userService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany()
    private List<Gym> favouriteGyms;
}
