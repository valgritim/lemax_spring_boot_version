package com.fan_de_lemax.lemax.models.services;

import com.fan_de_lemax.lemax.models.entities.User;
import com.fan_de_lemax.lemax.models.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public void deleteUser(Long id) {
      userRepository.deleteById(id);
  }

  @Override
  public Optional<User> saveOrUpdateUser(User user) {
    return Optional.of(userRepository.saveAndFlush(user));
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }




}
