package pl.fitfinder.microservices.fitfinder.userService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;
    private String country;
    private String city;
    private String street;
    @OneToOne
    @JoinColumn(name = "gym_id")
    @JsonIgnore
    private Gym gym;
}