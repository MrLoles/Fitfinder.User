package pl.fitfinder.microservices.fitfinder.userService.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.fitfinder.microservices.fitfinder.userService.model.Gym;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Integer> {
    Optional<Gym> findByGymName(String gymName);
}
