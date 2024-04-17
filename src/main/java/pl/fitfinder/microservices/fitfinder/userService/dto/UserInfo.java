package pl.fitfinder.microservices.fitfinder.userService.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String email;
    private String username;
}
