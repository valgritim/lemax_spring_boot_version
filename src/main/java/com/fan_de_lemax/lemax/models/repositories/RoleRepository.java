package com.fan_de_lemax.lemax.models.repositories;

import com.fan_de_lemax.lemax.models.entities.Role;
import com.fan_de_lemax.lemax.security.EnumRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
      Optional<Role> findByRoleName(EnumRole name);
   }
