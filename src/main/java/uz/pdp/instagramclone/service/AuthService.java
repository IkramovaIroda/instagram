package uz.pdp.instagramclone.service;

//import com.example.soliqjwttask.entity.User;
//import com.example.soliqjwttask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.instagramclone.entity.User;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.payload.LoginDTO;
import uz.pdp.instagramclone.payload.ShowUser;
import uz.pdp.instagramclone.repository.UserRepository;
import uz.pdp.instagramclone.security.JwtProvider;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


    public ApiResponse login(LoginDTO dto) {
        //email,phoneNumber,username bo'lishi mumkin
        String login = dto.getLogin();

        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(login) || user.getUsername().equals(login)|| user.getPhoneNumber().equals(login)){
                if (user.getPassword().equals(dto.getPassword())){
                    String token = jwtProvider.generateToken(user.getUsername());
                    return new ApiResponse("User found and token :"+token,true,user);
                }
            }
        }
        return new ApiResponse("Something went wrong",false);
    }

    public ApiResponse searchByUserName(String username, Pageable pageable) {
        Page<User> allByNameContains = userRepository.findAllByNameContains(username, pageable);

        return new ApiResponse("Found",true,allByNameContains);
    }

    public ApiResponse getOneUser(Long current_user_id, Long user_profile) {
        if (userRepository.existsById(current_user_id)) {
            if (userRepository.existsById(user_profile)) {
                User currentUser = userRepository.getById(current_user_id);
                User userProfile = userRepository.getById(user_profile);

                ShowUser showUser = new ShowUser();
                showUser.setUser(userProfile);
                showUser.setFollowed(currentUser.getFollowing().contains(userProfile));
                return new ApiResponse("User found",true,showUser);
            }
        }
        return new ApiResponse("Something went wrong",false);
    }
}
