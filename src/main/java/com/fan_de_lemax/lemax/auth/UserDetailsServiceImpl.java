package com.fan_de_lemax.lemax.auth;

import com.fan_de_lemax.lemax.models.entities.User;
import com.fan_de_lemax.lemax.models.repositories.UserRepository;
import com.fan_de_lemax.lemax.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserService userService;

   @Autowired
   public UserDetailsServiceImpl(UserService userService) {
      this.userService = userService;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userService.getUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found", username)));
      return UserDetailsImpl.build(user);
   }
}
