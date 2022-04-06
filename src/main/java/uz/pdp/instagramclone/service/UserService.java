package uz.pdp.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.instagramclone.entity.Registration;
import uz.pdp.instagramclone.entity.User;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    final UserRepository userRepository;

    public ApiResponse addUser(Registration registration) {
        if (userRepository.existsByUsernameIgnoreCase(registration.getUsername())) {
            return new ApiResponse("This username isn't available. Please try another.", false);
        }
        if (userRepository.existsByEmailIgnoreCase(registration.getRegister())) {
            return new ApiResponse("This email isn't available. Please try another.", true);
        }
        User user = new User();

        boolean contains = registration.getRegister().contains("@");
        if (contains) {
            user.setEmail(registration.getRegister());
            user.setName(registration.getFullName());
            user.setUsername(registration.getUsername());
            user.setPassword(registration.getPassword());
        } else {
            user.setPhoneNumber(registration.getRegister());
            user.setName(registration.getFullName());
            user.setUsername(registration.getUsername());
            user.setPassword(registration.getPassword());
        }
        userRepository.save(user);
        return new ApiResponse("Successfully registered!", true);
    }
}
