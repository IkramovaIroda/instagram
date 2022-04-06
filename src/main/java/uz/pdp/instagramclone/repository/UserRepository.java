package uz.pdp.instagramclone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.instagramclone.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameIgnoreCase(String username);
    //existsByNameIgnoreCase
    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);
    boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByUsername(String username);

    Page<User> findAllByNameContains(String username, Pageable pageable);

}
