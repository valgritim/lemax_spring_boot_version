package com.fan_de_lemax.lemax.models.repositories;

import com.fan_de_lemax.lemax.models.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);
   Boolean existsByUsername(String username);

}
