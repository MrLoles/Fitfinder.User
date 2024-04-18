package pl.fitfinder.microservices.fitfinder.userService.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fitfinder.microservices.fitfinder.userService.dto.AdministratedGyms;
import pl.fitfinder.microservices.fitfinder.userService.dto.GymDTOAddFavourites;
import pl.fitfinder.microservices.fitfinder.userService.dto.UserInfo;
import pl.fitfinder.microservices.fitfinder.userService.exception.exceptions.GymNotFound;
import pl.fitfinder.microservices.fitfinder.userService.exception.exceptions.UserNotFound;
import pl.fitfinder.microservices.fitfinder.userService.model.Gym;
import pl.fitfinder.microservices.fitfinder.userService.model.User;
import pl.fitfinder.microservices.fitfinder.userService.repository.GymRepository;
import pl.fitfinder.microservices.fitfinder.userService.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.fitfinder.microservices.fitfinder.userService.utils.JwtTokenManager.getUserId;

@Service
public class UserService {
    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addGymToFavourites(String token, GymDTOAddFavourites gymDTO){
        int idUser = Integer.parseInt(getUserId(token));

        Optional<Gym> gym = gymRepository.findById(gymDTO.getId());
        Gym gymToSave = gym.orElseThrow(() -> new GymNotFound("No matching gym with id:" + gymDTO.getId()));

        Optional<User> user = userRepository.findById(idUser);
        User userForSavingGym = user.orElseThrow(() -> new UserNotFound("No matching user with id:" + idUser));

        List<Gym> favouriteGyms = userForSavingGym.getFavouriteGyms();

        Optional<Gym> existingGym = favouriteGyms.stream()
                .filter(favGym -> favGym.getId() == gymDTO.getId())
                .findFirst();

        if (existingGym.isPresent()) {
            favouriteGyms.remove(existingGym.get());
        } else {
            favouriteGyms.add(gymToSave);
        }

        userRepository.save(userForSavingGym);
    }

    public List<Gym> getFavourites(String token){
        int idUser = Integer.parseInt(getUserId(token));

        User user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFound("No matching user with id:" + idUser));
        return user.getFavouriteGyms();
    }

    public boolean checkFavourite(String token, int gymId) {
        int idUser = Integer.parseInt(getUserId(token));

        Optional<User> user = userRepository.findById(idUser);
        User userToCheck = user.orElseThrow(() -> new UserNotFound("No matching user with id:" + idUser));


        Optional<Gym> existingGym = userToCheck.getFavouriteGyms().stream()
                .filter(favGym -> favGym.getId() == gymId)
                .findFirst();

        return existingGym.isPresent();
    }

    public UserInfo getUserInfo(String token) {
        int idUser = Integer.parseInt(getUserId(token));
        User user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFound("No matching user with id:" + idUser));

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(user.getEmail());
        userInfo.setUsername(user.getUsername());
        return userInfo;
    }

    public List<AdministratedGyms> getAdministratedGyms(String token) {
        int idUser = Integer.parseInt(getUserId(token));
        User user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFound("No matching user with id:" + idUser));
        List<AdministratedGyms> administratedGyms = new ArrayList<>();

        user.getAdministratedGyms().forEach(gym -> administratedGyms.add(new AdministratedGyms(gym.getGymName(), gym.getImgUrl())));

        return administratedGyms;
    }
}
