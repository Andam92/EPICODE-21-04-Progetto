package it.epicode.weekly_project.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.weekly_project.auth.entity.Device;
import it.epicode.weekly_project.auth.entity.User;
import it.epicode.weekly_project.auth.payload.LoginDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    
    @Query (value = "SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(String username);
    

}
