package com.fan_de_lemax.lemax.models.services;

import com.fan_de_lemax.lemax.models.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> getUserByUsername(String username);
  Optional<User> getUserById(Long id);
  void deleteUser(Long id);
  Boolean saveOrUpdateUser(User user);
  List<User> getAllUsers();


}
