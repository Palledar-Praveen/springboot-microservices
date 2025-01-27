package com.Cybertrons.springboot_restful_webservices.service;

import com.Cybertrons.springboot_restful_webservices.dto.UserDto;
import com.Cybertrons.springboot_restful_webservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    User getUserById(Long userId);
    List<User> getAllUsers();
    User updateUser(User user);
  //  List<User> updateUsers(List<User> users);
    void deleteUserById(Long userId);
}
