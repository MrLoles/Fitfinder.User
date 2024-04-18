package pl.fitfinder.microservices.fitfinder.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdministratedGyms {
    private String gymName;
    private String imgUrl;
}
