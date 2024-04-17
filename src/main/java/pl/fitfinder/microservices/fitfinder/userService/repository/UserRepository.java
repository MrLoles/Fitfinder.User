package pl.fitfinder.microservices.fitfinder.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fitfinder.microservices.fitfinder.userService.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
