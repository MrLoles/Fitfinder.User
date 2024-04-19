package pl.fitfinder.microservices.fitfinder.userService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String email;
    private String username;

    @OneToMany()
    private List<Gym> favouriteGyms;

    @JsonIgnore
    @ManyToMany(mappedBy = "administrators")
    private List<Gym> administratedGyms;
}
