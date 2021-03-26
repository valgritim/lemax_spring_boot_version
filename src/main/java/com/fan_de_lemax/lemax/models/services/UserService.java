package com.fan_de_lemax.lemax.models.services;

import com.fan_de_lemax.lemax.models.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> getUserByEmail(String email);
  Optional<User> getUserById(Long id);
  void deleteUser(Long id);
  Optional<User> saveOrUpdateUser(User user);
  List<User> getAllUsers();


}
