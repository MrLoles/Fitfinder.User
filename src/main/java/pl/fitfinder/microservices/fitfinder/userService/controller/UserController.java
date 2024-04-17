package pl.fitfinder.microservices.fitfinder.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.fitfinder.microservices.fitfinder.userService.dto.GymDTOAddFavourites;
import pl.fitfinder.microservices.fitfinder.userService.model.Gym;
import pl.fitfinder.microservices.fitfinder.userService.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addGymToFavourites")
    public boolean addGymToFavourite(@RequestHeader("token") String token, @RequestBody GymDTOAddFavourites gym){
        userService.addGymToFavourites(token, gym);
        return true;
    }

    @GetMapping("/favourites")
    public List<Gym> getFavouritesGyms(@RequestHeader("token") String token){
        return userService.getFavourites(token);
    }

    @GetMapping("/favourites/{gymId}")
    public boolean checkIfGymIsFavourite(@RequestHeader("token") String token, @PathVariable int gymId){
        return userService.checkFavourite(token, gymId);
    }
}